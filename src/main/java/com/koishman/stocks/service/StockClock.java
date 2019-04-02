package com.koishman.stocks.service;

import com.google.common.collect.Lists;
import com.koishman.stocks.auth.repository.Symbol;
import com.koishman.stocks.auth.repository.SymbolRepository;
import com.koishman.stocks.model.sotck.Stock;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Component
public class StockClock {

	private static final Logger log = LoggerFactory.getLogger(StockClock.class);

	private AtomicBoolean isMarketOpen = new AtomicBoolean(false);
	@Autowired
	private StockService stockService;
	@Autowired
	private SymbolRepository symbolRepository;
	@Autowired
	private StockContext stockContext;

	List<Timer> stockTasks;
	List<Symbol> symbolsToRun;

	private BlockingDeque<List<Symbol>> symbols;

	@PostConstruct
	public void init() {
		stockContext.clean();
		symbolsToRun = symbolRepository.findAllByShouldRunTrue();
		List<List<Symbol>> partition = ListUtils.partition(symbolsToRun, 10);
		stockTasks = Lists.newArrayList();
		symbols = new LinkedBlockingDeque<>(partition);

	}

	@Scheduled(cron = " */60 10-59 7,16 * *  MON-FRI", zone = "GMT-4:00")
	public void validateMarket() {
		log.info("validating market");
		boolean marketStatus = stockService.isMarketOpen(Lists.newArrayList("EBAY"));
		isMarketOpen.set(marketStatus);
//		List<Symbol> allByShouldRun = symbolRepository.findAllByShouldRunTrue();
//		Map<Long, Symbol> allByShouldRunMap = allByShouldRun.stream().collect(Collectors.toMap(Symbol::getId, symbol -> symbol));
//		//		if (allByShouldRun.size() != symbolsToRun.size()) {
//		Map<Long, Symbol> symbolsToRunMap = symbolsToRun.stream().collect(Collectors.toMap(Symbol::getId, symbol -> symbol));
//		allByShouldRunMap.remove(symbolsToRunMap);
//		List<List<Symbol>> partition = ListUtils.partition(Lists.newArrayList(allByShouldRunMap.values()), 10);
//		symbols.addAll(partition);
//		//		symbolsToRun.forEach(symbolsToRun -> log.info(symbolsToRun.toString()));
//		//		symbols.forEach(list -> log.info(list.toString()));
//		for (List<Symbol> symbolList : symbols) {
//			for (Symbol symbol : symbolList) {
//				System.out.println(symbol);
//			}
//		}
//		System.out.println("symbolToRun ");
//		for (Symbol symbol : symbolsToRun) {
//			System.out.println(symbol);
//		}
//		//		}
		cleanTaskList();
	}

	private void cleanTaskList() {
		log.info("cleaning task list");
		if (isMarketOpen.get() == false) {
			for (Timer stockTask : stockTasks) {
				stockTask.cancel();
				stockTask.purge();
			}
			//			List<Symbol> symbolsToRun = symbolRepository.findAllByShouldRunTrue();
			//			List<List<Symbol>> partition = ListUtils.partition(symbolsToRun, 10);
			//			symbols = new LinkedBlockingDeque<>(partition);
			stockTasks = Lists.newArrayList();
		}
	}

	@Scheduled(initialDelay = 10000, fixedRate = 60000)
	public void startRecording() {
		log.info("start recording method is working");
		if (isMarketOpen.get()) {
			while (!symbols.isEmpty()) {
				List<Symbol> polledList = symbols.poll();
				List<String> symbolList = polledList.stream().map(Symbol::getSymbol).collect(Collectors.toList());
				prepareHistory(symbolList);
				StockTask stockTask = new StockTask(stockService, stockContext, symbolList);
				Timer timer = new Timer(true);
				timer.scheduleAtFixedRate(stockTask, 0, 50 * 1000);
				stockTask.run();
				stockTasks.add(timer);
			}

		}
	}

	private void prepareHistory(List<String> symbols) {
		stockContext.clean();
		Map<String, List<Stock>> dayHistory = stockService.getDayHistory(symbols);
		for (Map.Entry<String, List<Stock>> entry : dayHistory.entrySet()) {
			fillNoneValueFields(entry.getValue());
			int sum = entry.getValue().stream().mapToInt(Stock::getVolume).sum();
			int num = 0;
			for (Stock stock : entry.getValue()) {
				num += stock.getVolume();
			}
			System.out.println(sum);
			System.out.println(num);
			stockContext.addToContextHistory(entry.getKey(), entry.getValue(), sum);

		}

	}

	private void fillNoneValueFields(List<Stock> stocks) {
		if (CollectionUtils.isNotEmpty(stocks)) {
			for (int i = 1; i < stocks.size(); i++) {
				if (stocks.get(i).getValue() == -1) {
					stocks.get(i).setValue(stocks.get(i - 1).getValue());
				}
			}
		}

	}

}
