package com.koishman.stocks.model.sotck;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chart {

	@JsonProperty("minute")
	private String minute;
	@JsonProperty("marketAverage")
	private Double average;
	@JsonProperty("marketVolume")
	private int volume;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("minute")
	public String getMinute() {
		return minute;
	}

	@JsonProperty("minute")
	public void setMinute(String minute) {
		this.minute = minute;
	}

	public Chart withMinute(String minute) {
		this.minute = minute;
		return this;
	}

	@JsonProperty("marketAverage")
	public Double getAverage() {
		return average;
	}

	@JsonProperty("marketAverage")
	public void setAverage(Double average) {
		this.average = average;
	}

	public Chart withAverage(Double average) {
		this.average = average;
		return this;
	}

	@JsonProperty("marketVolume")
	public int getVolume() {
		return volume;
	}

	@JsonProperty("marketVolume")
	public void setVolume(int volume) {
		this.volume = volume;
	}

	public Chart withVolume(int volume) {
		this.volume = volume;
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

	public Chart withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("minute", minute)
				.append("average", average)
				.append("additionalProperties", additionalProperties)
				.toString();
	}
}
