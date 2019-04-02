package com.koishman.stocks.service;

import com.google.common.collect.Lists;
import com.koishman.stocks.client.BatchClient.StockClientV1;
import com.koishman.stocks.model.sotck.BatchStock;
import com.koishman.stocks.model.sotck.Chart;
import com.koishman.stocks.model.sotck.Quote;
import com.koishman.stocks.model.sotck.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StockService {

	@Autowired
	private StockClientV1 stockClientV1;

	public Map<String, List<Stock>> getDayHistory(List<String> symbols) {
		Map<String, BatchStock> stockHistory = stockClientV1.getStockHistory(symbols);
		return convertHistoryToStock(stockHistory);

	}

	public Map<String, Stock> getCurrentValue(List<String> symbols) {
		Map<String, BatchStock> stockHistory = stockClientV1.getCurrentValue(symbols);
		return convertCurrentToStock(stockHistory);

	}

	public boolean isMarketOpen(List<String> symbols) {
		Map<String, BatchStock> stockHistory = stockClientV1.getQuotes(symbols);
		String calculatedPrice = stockHistory.entrySet().iterator().next().getValue().getQuote().getCalculationPrice();
		//		Set<Map.Entry<String, List<Stock>>> entries = convertToStock(stockHistory).entrySet();
		//		String calculatedPrice = entries.iterator().next().getValue().get(0).getCalculatedPrice();
		return calculatedPrice.equals("tops");

	}

	private Map<String, List<Stock>> convertToStock(Map<String, BatchStock> stockHistory) {
		Map<String, List<Stock>> stocksMap = new HashMap<>();
		for (Map.Entry<String, BatchStock> entry : stockHistory.entrySet()) {
			String companyName = entry.getValue().getQuote() != null ? entry.getValue().getQuote().getCompanyName() : null;
			String symbol = entry.getValue().getQuote() != null ? entry.getValue().getQuote().getSymbol() : null;
			String calculationPrice = entry.getValue().getQuote() != null ? entry.getValue().getQuote().getCalculationPrice() : null;
			List<Stock> stocks = Lists.newArrayList();
			for (Chart chart : entry.getValue().getChart()) {
				Stock stock = new Stock();
				stock.setTime(chart.getMinute() != null ?
						chart.getMinute() :
						entry.getValue().getQuote().getLatestUpdate() != null ? convertDate(entry.getValue().getQuote().getLatestUpdate()) : null);
				stock.setValue(chart.getAverage() != null ?
						chart.getAverage() :
						String.valueOf(entry.getValue().getQuote().getLatestPrice()) != null ?
								Double.valueOf(entry.getValue().getQuote().getLatestPrice()) :
								null);
				stock.setCompanyName(companyName);
				stock.setSymbol(symbol);
				stock.setCalculatedPrice(calculationPrice);
				stock.setVolume(chart.getVolume());
				stocks.add(stock);
			}
			stocksMap.put(entry.getKey(), stocks);
		}
		return stocksMap;
	}

	private Map<String, List<Stock>> convertHistoryToStock(Map<String, BatchStock> stockHistory) {
		Map<String, List<Stock>> stocksMap = new HashMap<>();
		for (Map.Entry<String, BatchStock> entry : stockHistory.entrySet()) {
			String companyName = entry.getValue().getQuote() != null ? entry.getValue().getQuote().getCompanyName() : null;
			String symbol = entry.getValue().getQuote() != null ? entry.getValue().getQuote().getSymbol() : null;
			//			String calculationPrice = entry.getValue().getQuote() != null ? entry.getValue().getQuote().getCalculationPrice() : null;
			List<Stock> stocks = Lists.newArrayList();
			for (Chart chart : entry.getValue().getChart()) {
				Stock stock = new Stock();
				stock.setTime(chart.getMinute());
				stock.setValue(chart.getAverage());
				stock.setCompanyName(companyName);
				stock.setSymbol(symbol);
				//				stock.setCalculatedPrice(calculationPrice);
				stock.setVolume(chart.getVolume());
				stocks.add(stock);
			}
			stocksMap.put(entry.getKey(), stocks);
		}
		return stocksMap;
	}

	private Map<String, Stock> convertCurrentToStock(Map<String, BatchStock> stockHistory) {
		Map<String, Stock> stocksMap = new HashMap<>();
		for (Map.Entry<String, BatchStock> entry : stockHistory.entrySet()) {
			Quote quote = entry.getValue().getQuote();
			String companyName = quote != null ? quote.getCompanyName() : null;
			String symbol = quote != null ? quote.getSymbol() : null;

			Stock stock = new Stock();
			stock.setTime(convertDate(quote.getLatestUpdate()));
			stock.setValue(quote.getLatestPrice());
			stock.setCompanyName(companyName);
			stock.setSymbol(symbol);
			stock.setVolume(quote.getLatestVolume());
			stocksMap.put(entry.getKey(), stock);
		}
		return stocksMap;
	}

	private String convertDate(String dateEpoch) {
		ZonedDateTime zonedDateTime = Instant.ofEpochMilli(Long.parseLong(dateEpoch)).atZone(ZoneId.of("America/New_York"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

		String formatDateTime = zonedDateTime.format(formatter);
		return formatDateTime;
	}
}
