package com.koishman.stocks.service;

import com.koishman.stocks.model.sotck.Stock;

import java.util.List;
import java.util.Map;
import java.util.TimerTask;

public class StockTask extends TimerTask {

	private StockContext stockContext;
	private StockService stockService;
	private final List<String> symbolsList;

	public StockTask(StockService stockService, StockContext stockContext, List<String> symbolsList) {
		this.stockService = stockService;
		this.stockContext = stockContext;
		this.symbolsList = symbolsList;

	}

	public void run() {
		Map<String, List<Stock>> currentValue = stockService.getCurrentValue(symbolsList);
		for (String symbol : currentValue.keySet()) {
			if (!currentValue.get(symbol).isEmpty()) {
				stockContext.addToContext(symbol, currentValue.get(symbol));
			}
		}
	}
}
