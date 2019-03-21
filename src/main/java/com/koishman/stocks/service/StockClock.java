package com.koishman.stocks.service;

import com.google.common.collect.Lists;
import com.koishman.stocks.auth.repository.Symbol;
import com.koishman.stocks.auth.repository.SymbolRepository;
import com.koishman.stocks.model.sotck.Stock;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.stream.Collectors;

@Component
public class StockClock {

	private boolean isMarketOpen = true;
	private boolean alreadyRunning;
	int counter = 10;
	@Autowired
	private StockService stockService;
	@Autowired
	private SymbolRepository symbolRepository;
	@Autowired
	private StockContext stockContext;

	private List<List<Symbol>> symbols;

	@PostConstruct
	public void init() {
		stockContext.clean();
		List<Symbol> allByShouldRun = symbolRepository.findAllByShouldRunTrue();
		symbols = ListUtils.partition(allByShouldRun, 10);

	}

	@Async
	@Scheduled(fixedRate = 60000)
	public void isMarketOpen() {
		isMarketOpen = stockService.isMarketOpen(Lists.newArrayList("EBAY"));
		if (isMarketOpen && !alreadyRunning) {
			startRecording();
		}
	}

	public void startRecording() {
		alreadyRunning = true;
		System.out.println("start recording");
		List<Timer> stockTasks = Lists.newArrayList();
		if (isMarketOpen) {
			stockTasks = Lists.newArrayList();
			for (List<Symbol> symbol : symbols) {
				List<String> symbolList = symbol.stream().map(Symbol::getSymbol).collect(Collectors.toList());
				prepareHistory(symbolList);
				StockTask stockTask = new StockTask(stockService, stockContext, symbolList);
				Timer timer = new Timer(true);
				timer.scheduleAtFixedRate(stockTask, 0, 60 * 1000);
				stockTask.run();
				stockTasks.add(timer);
			}
			while (isMarketOpen) {
			}
			stockTasks.stream().forEach(timer -> timer.cancel());
		}

		alreadyRunning = false;
	}

	private void prepareHistory(List<String> symbols) {
		Map<String, List<Stock>> dayHistory = stockService.getDayHistory(symbols);
		for (Map.Entry<String, List<Stock>> entry : dayHistory.entrySet()) {
			stockContext.addToContext(entry.getKey(), entry.getValue());

		}

	}
}
