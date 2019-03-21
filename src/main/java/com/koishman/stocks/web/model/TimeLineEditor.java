package com.koishman.stocks.web.model;

import java.beans.PropertyEditorSupport;

public class TimeLineEditor extends PropertyEditorSupport {

	//This will be called when user HTTP Post to server a field bound to TimeLine
	@Override
	public void setAsText(String id) {
		TimeLine d;
		switch (Integer.parseInt(id)) {
			case 1:
				d = new TimeLine(1, "today");
				break;
			case 2:
				d = new TimeLine(2, "schedualed");
				break;
			default:
				d = null;
		}
		this.setValue(d);
	}
}