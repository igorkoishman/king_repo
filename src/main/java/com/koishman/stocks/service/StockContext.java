package com.koishman.stocks.service;

import com.koishman.stocks.model.sotck.Stock;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

@Component
public class StockContext {

	private Map<String, BlockingDeque<Stock>> context;
	private Map<String, TimeAndVolume> lastDatePerSymbol;

	public void clean() {
		this.context = new ConcurrentHashMap<>();
		this.lastDatePerSymbol = new ConcurrentHashMap<>();
	}

	public void addStockToContext(String symbol, Stock stock) {
		if (!stock.getTime().equals(lastDatePerSymbol.get(symbol).getTime())) {
			BlockingDeque<Stock> queue = context.getOrDefault(symbol, new LinkedBlockingDeque<>());
			queue.add(stock);
			TimeAndVolume timeAndVolume = lastDatePerSymbol.get(symbol);
			if (timeAndVolume != null) {
				String currentTime = new String(stock.getTime());
				int currentVolume = stock.getVolume();
				stock.setVolume(stock.getVolume() - lastDatePerSymbol.get(symbol).getVolume());
				timeAndVolume.setTime(currentTime);
				timeAndVolume.setVolume(currentVolume);
			} else {
				TimeAndVolume tv = new TimeAndVolume();
				String currentTime = new String(stock.getTime());
				int currentVolume = stock.getVolume();
				tv.setTime(currentTime);
				tv.setVolume(currentVolume);
				lastDatePerSymbol.put(symbol, tv);
			}
			context.put(symbol, queue);
		}
	}

	public BlockingDeque<Stock> getSymbolStockData(String symbol) {
		if (!MapUtils.isEmpty(context)) {
			BlockingDeque<Stock> queue = context.get(symbol);
			Stock stock = queue.peekLast();
			if (stock != null) {
				return queue;
			}

		}
		return new LinkedBlockingDeque();
	}

	public Set<String> getSymbols() {
		if (!MapUtils.isEmpty(context)) {
			return context.keySet();
		}
		return new HashSet<>();
	}

	public void addToContextHistory(String symbol, List<Stock> stocks, int sum) {
		BlockingDeque<Stock> queue = context.getOrDefault(symbol, new LinkedBlockingDeque<>());
		queue.addAll(stocks);
		context.put(symbol, queue);
		TimeAndVolume timeAndVolume = new TimeAndVolume(stocks.get(stocks.size() - 1).getTime(), sum);
		lastDatePerSymbol.put(symbol, timeAndVolume);

	}
}
