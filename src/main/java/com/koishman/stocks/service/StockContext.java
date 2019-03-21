package com.koishman.stocks.service;

import com.google.common.collect.Lists;
import com.koishman.stocks.model.sotck.Stock;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class StockContext {

	private Map<String, ConcurrentLinkedQueue<Stock>> context;
	private String lastTimeFetched;

	public void clean() {
		this.context = new ConcurrentHashMap<>();
	}

	public void addToContext(String symbol, List<Stock> stocks) {
		if (stocks.get(0).getTime().equals(lastTimeFetched))
			stocks.remove(0);
		ConcurrentLinkedQueue<Stock> queue = context.getOrDefault(symbol, new ConcurrentLinkedQueue<Stock>());
		queue.addAll(stocks);
		context.put(symbol, queue);
	}

	public Map<String, ConcurrentLinkedQueue<Stock>> getQueue() {
		if (!MapUtils.isEmpty(context)) {
			int size = context.entrySet().iterator().next().getValue().size();
			if (size != 0) {
				ArrayList<Stock> stocks = Lists.newArrayList(context.entrySet().iterator().next().getValue());
				lastTimeFetched = stocks.get(size - 1).getTime();
			}
		}
		return context;
	}

}
