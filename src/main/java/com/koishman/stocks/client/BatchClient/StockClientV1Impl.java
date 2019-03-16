package com.koishman.stocks.client.BatchClient;

import com.google.common.collect.Lists;
import com.koishman.stocks.model.sotck.BatchStock;
import com.koishman.stocks.service.BaseClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StockClientV1Impl extends BaseClient implements StockClientV1 {

	private static final String defaultBaseUrl = "https://api.iextrading.com";
	private static final String defaultBaseVersion = "/1.0";
	private static final String batchStockPath = "/stock/market/batch";

	private static final ParameterizedTypeReference<Map<String, BatchStock>> BATCH_STOCK_RESPONSE = new ParameterizedTypeReference<Map<String, BatchStock>>() {

	};

	@Override
	public Map<String, BatchStock> getStockHistoryAndCurrent(List<String> symbols) {
		List<String> filters = Lists.newArrayList("latestPrice,minute,average,symbol,companyName,calculationPrice,latestUpdate");
		return execute(symbols, filters).getBody();
	}
	@Override
	public Map<String, BatchStock> getStockHistory(List<String> symbols) {
		List<String> filters = Lists.newArrayList("minute,average,symbol,companyName");
		return execute(symbols, filters).getBody();
	}

	@Override
	public Map<String, BatchStock> getCurrentValue(List<String> symbols) {
		List<String> filters = Lists.newArrayList("latestPrice,symbol,companyName,latestUpdate,calculationPrice");
		return execute(symbols, filters).getBody();
	}

	private ResponseEntity<Map<String, BatchStock>> execute(List<String> symbols, List<String> filters) {
		URI uri = generateBaseURI(this::generateUri, defaultBaseUrl, defaultBaseVersion, batchStockPath, symbols, filters);
		return super.execute(uri, HttpMethod.GET, getHttpHeader(), BATCH_STOCK_RESPONSE);
	}

	protected HttpHeaders getHttpHeader() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("accept", "application/json");
		return httpHeaders;
	}

	private URI generateBaseURI(URIGenerator uriGenerator, String url, String version, String relativePatch, List<String> symbols,
			List<String> filters) {
		String path = new StringBuilder().append(url).append(version).append(relativePatch).toString();
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("symbols", StringUtils.join(symbols, ','));
		queryParams.put("filter", StringUtils.join(filters, ','));
		URI uri = uriGenerator.generateUri(path, queryParams);
		return uri;
	}

	private URI generateUri(String path, Map<String, String> queryParams) {
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(path)
				.queryParam("range", "1d")
				.queryParam("types", "quote,chart");
		for (Map.Entry<String, String> entry : queryParams.entrySet()) {
			uriComponentsBuilder.queryParam(entry.getKey(), entry.getValue());
		}
		return uriComponentsBuilder.build().encode().toUri();

	}

}

