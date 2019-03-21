package com.koishman.stocks.web.model;

public class TimeLine
{


	private Integer id;
	private String name;

	public TimeLine(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//Setters and Getters

	@Override
	public String toString() {
		return "TimeLine [id=" + id + ", name=" + name + "]";
	}
}