//package com.koishman.stocks.service;
//
//import com.google.common.collect.Lists;
//import com.koishman.stocks.client.BatchClient.StockClientV1Impl;
//import com.koishman.stocks.model.sotck.BatchStock;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestOperations;
//import org.springframework.web.client.RestTemplate;
//
//import javax.annotation.PostConstruct;
//import java.util.Map;
//
//@Component
//public class TopRatedPost {
//
//	private static final Logger log = LoggerFactory.getLogger(TopRatedPost.class);
//	private RestOperations restTemplate;
//
//	@Autowired
//	StockClientV1Impl stockClientV1;
//
//	@PostConstruct
//	public void initRest() {
//		restTemplate = new RestTemplate();
//	}
//
////	@Scheduled(fixedRate = 1000)
////	@Async
//	public void run() {
//
//		ResponseEntity<Map<String, BatchStock>> request = stockClientV1.getStockHistory(Lists.newArrayList("ebay"));
//
//		for (Map.Entry<String, BatchStock> o : request.getBody().entrySet()) {
//			System.out.println(o.getKey() + "-----------" + o.getValue());
//
//		}
//	}
//
//	//	private ResponseEntity createRequest() {
//	//		RestTemplate restTemplate = new RestTemplate();
//	//	https://api.iextrading.com/1.0/stock/market/batch?symbols=aapl,ebay&types=quote,chart&range=1d&filter=latestPrice,minute,average,latestTime
//	//		String url = "https://api.iextrading.com/1.0/stock/market/batch";
//	//		URI uri = UriComponentsBuilder.fromHttpUrl(url)
//	//				.queryParam("symbols", "aapl,ebay")
//	//				.queryParam("types", "quote,chart")
//	//				.queryParam("range", "1d")
//	//				.queryParam("filter", "latestPrice,minute,average,symbol,companyName,calculationPrice")
//	//				.build()
//	//				.encode()
//	//				.toUri();
//	//		HttpHeaders httpHeaders = new HttpHeaders();
//	//
//	//		httpHeaders.add("accept", "application/json");
//	//		HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
//	//
//	//		ResponseEntity<Map<String, BatchStock>> response = restTemplate.exchange(uri, HttpMethod.GET, entity,
//	//				new ParameterizedTypeReference<Map<String, BatchStock>>() {
//	//
//	//				});
//	//		return response;
//	//	}
//
//}