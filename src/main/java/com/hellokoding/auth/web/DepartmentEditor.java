package com.hellokoding.auth.web;

import java.beans.PropertyEditorSupport;

public class DepartmentEditor extends PropertyEditorSupport {

	//This will be called when user HTTP Post to server a field bound to DepartmentVO
	@Override
	public void setAsText(String id) {
		DepartmentVO d;
		switch (Integer.parseInt(id)) {
			case 1:
				d = new DepartmentVO(1, "today");
				break;
			case 2:
				d = new DepartmentVO(2, "schedualed");
				break;
			default:
				d = null;
		}
		this.setValue(d);
	}
}