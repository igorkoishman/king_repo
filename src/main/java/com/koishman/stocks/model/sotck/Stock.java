package com.koishman.stocks.model.sotck;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Stock {

	private String symbol;

	private String companyName;

	private String value;

	private String time;

	private String calculatedPrice;

	public Stock() {
	}

	public Stock(String symbol, String companyName, String value, String time) {
		this.symbol = symbol;
		this.companyName = companyName;
		this.value = value;
		this.time = time;
	}

	public String getCalculatedPrice() {
		return calculatedPrice;
	}

	public void setCalculatedPrice(String calculatedPrice) {
		this.calculatedPrice = calculatedPrice;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("symbol", symbol)
				.append("companyName", companyName)
				.append("value", value)
				.append("time", time)
				.toString();
	}
}
