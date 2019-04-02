package com.koishman.stocks.service;

public class TimeAndVolume {

	private String time;
	private int volume;

	public TimeAndVolume() {
	}

	public TimeAndVolume(String time, int volume) {
		this.time = time;
		this.volume = volume;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
}
