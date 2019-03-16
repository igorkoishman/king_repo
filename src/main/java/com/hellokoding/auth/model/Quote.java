package com.hellokoding.auth.model;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Quote {

	@JsonProperty("latestPrice")
	private Double latestPrice;
	@JsonProperty("calculationPrice")
	private String calculationPrice;
	@JsonProperty("symbol")
	private String symbol;
	@JsonProperty("companyName")
	private String companyName;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("latestPrice")
	public Double getLatestPrice() {
		return latestPrice;
	}

	@JsonProperty("latestPrice")
	public void setLatestPrice(Double latestPrice) {
		this.latestPrice = latestPrice;
	}

	public Quote withLatestPrice(Double latestPrice) {
		this.latestPrice = latestPrice;
		return this;
	}

	@JsonProperty("calculationPrice")
	public String getCalculationPrice() {
		return calculationPrice;
	}

	@JsonProperty("calculationPrice")
	public void setCalculationPrice(String calculationPrice) {
		this.calculationPrice = calculationPrice;
	}

	public Quote withCalculationPrice(String calculationPrice) {
		this.calculationPrice = calculationPrice;
		return this;
	}

	@JsonProperty("symbol")
	public String getSymbol() {
		return symbol;
	}

	@JsonProperty("symbol")
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Quote withSymbol(String symbol) {
		this.symbol = symbol;
		return this;
	}

	@JsonProperty("companyName")
	public String getCompanyName() {
		return companyName;
	}

	@JsonProperty("companyName")
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Quote withCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public Quote withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("latestPrice", latestPrice)
				.append("calculationPrice", calculationPrice)
				.append("symbol", symbol)
				.append("companyName", companyName)
				.append("additionalProperties", additionalProperties)
				.toString();
	}
}
