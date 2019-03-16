package com.hellokoding.auth.model;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.zankowski.iextrading4j.api.util.ListUtil;

import java.io.Serializable;
import java.util.List;

@JsonPropertyOrder({ "chart", "quote" })
public class BatchStocks implements Serializable {

	private final List<Chart> chart;
	private final Quote quote;

	@JsonCreator
	public BatchStocks(@JsonProperty("chart") List<Chart> chart, @JsonProperty("quote") Quote quote) {
		this.chart = ListUtil.immutableList(chart);
		this.quote = quote;
	}

	public List<Chart> getChart() {
		return this.chart;
	}

	public Quote getQuote() {
		return this.quote;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BatchStocks that = (BatchStocks) o;
		return java.util.Objects.equals(chart, that.chart) && java.util.Objects.equals(quote, that.quote);
	}

	@Override
	public int hashCode() {

		return java.util.Objects.hash(chart, quote);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("chart", chart).append("quote", quote).toString();
	}
}
