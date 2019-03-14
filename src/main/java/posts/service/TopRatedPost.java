package posts.service;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import posts.model.BatchStocks;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

@Component
public class TopRatedPost {

	private static final Logger log = LoggerFactory.getLogger(TopRatedPost.class);
	private RestOperations restTemplate;

	@PostConstruct
	public void initRest() {
		restTemplate = new RestTemplate();
	}

	@Scheduled(fixedRate = 1000)
	@Async
	public void run() throws IOException, JSONException {

		ResponseEntity<Map<String, BatchStocks>> request = createRequest();

		for (Map.Entry<String, BatchStocks> o : request.getBody().entrySet()) {
			System.out.println(o.getKey() + "-----------" + o.getValue());

		}
	}

	private ResponseEntity createRequest() throws JSONException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		//    https://api.iextrading.com/1.0/stock/market/batch?symbols=aapl,ebay&types=quote,chart&range=1d&filter=latestPrice,minute,average,latestTime
		String url = "https://api.iextrading.com/1.0/stock/market/batch";
		URI uri = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("symbols", "aapl,ebay")
				.queryParam("types", "quote,chart")
				.queryParam("range", "1d")
				.queryParam("filter", "latestPrice,minute,average,symbol,companyName,calculationPrice")
				.build()
				.encode()
				.toUri();
		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add("accept", "application/json");
		HttpEntity<?> entity = new HttpEntity<>(httpHeaders);

		ResponseEntity<Map<String, BatchStocks>> response = restTemplate.exchange(uri, HttpMethod.GET, entity,
				new ParameterizedTypeReference<Map<String, BatchStocks>>() {

				});
		return response;
	}

}