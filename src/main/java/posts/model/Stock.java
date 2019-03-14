package posts.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Stock {

	private String symbol;
	private String companyName;
	private List<Chart> charts;
	private Quote quote;

	public Stock() {
	}

	public Stock(String symbol, String companyName, List<Chart> charts, Quote quote) {
		this.symbol = symbol;
		this.companyName = companyName;
		this.charts = charts;
		this.quote = quote;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("symbol", symbol)
				.append("companyName", companyName)
				.append("chart", charts)
				.append("quote", quote)
				.toString();
	}
}
