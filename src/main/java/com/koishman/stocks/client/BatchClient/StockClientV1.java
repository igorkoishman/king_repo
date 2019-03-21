package com.koishman.stocks.client.BatchClient;

import com.koishman.stocks.model.sotck.BatchStock;

import java.util.List;
import java.util.Map;

public interface StockClientV1 {

	Map<String, BatchStock> getStockHistory(List<String> symbols);

	Map<String, BatchStock> getCurrentValue(List<String> symbols);

	Map<String, BatchStock> getStockHistoryAndCurrent(List<String> symbols);

	Map<String, BatchStock> getQuotes(List<String> symbol);

}
