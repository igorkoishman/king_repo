package com.koishman.stocks.service;

import com.google.common.collect.Lists;
import com.koishman.stocks.client.BatchClient.StockClientV1;
import com.koishman.stocks.model.sotck.BatchStock;
import com.koishman.stocks.model.sotck.Chart;
import com.koishman.stocks.model.sotck.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StockService {

	@Autowired
	private StockClientV1 stockClientV1;

	public Map<String, List<Stock>> getDayHistory(List<String> symbols) {
		Map<String, BatchStock> stockHistory = stockClientV1.getStockHistory(symbols);
		return convertToStock(stockHistory);

	}

	private Map<String, List<Stock>> convertToStock(Map<String, BatchStock> stockHistory) {
		Map<String, List<Stock>> stocksMap = new HashMap<>();
		for (Map.Entry<String, BatchStock> entry : stockHistory.entrySet()) {
			String companyName = entry.getValue().getQuote().getCompanyName();
			String symbol = entry.getValue().getQuote().getSymbol();
			List<Stock> stocks = Lists.newArrayList();
			for (Chart chart : entry.getValue().getChart()) {
				Stock stock = new Stock();
				stock.setTime(chart.getMinute());
				stock.setValue(String.valueOf(chart.getAverage()));
				stock.setCompanyName(companyName);
				stock.setSymbol(symbol);
				stocks.add(stock);
			}
			stocksMap.put(entry.getKey(), stocks);
		}
		return stocksMap;
	}
}
