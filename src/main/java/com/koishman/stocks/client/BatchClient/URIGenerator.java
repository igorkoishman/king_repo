package com.koishman.stocks.client.BatchClient;

import java.net.URI;
import java.util.Map;

@FunctionalInterface
public interface URIGenerator {

	URI generateUri(String url, Map<String, String> queryParas);
}