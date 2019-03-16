package com.hellokoding.auth;

import com.hellokoding.auth.web.DepartmentVO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class EmployeeVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty
	private String symbols;
	@NotNull
	private DepartmentVO departmentVO;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSymbols() {
		return symbols;
	}

	public void setSymbols(String symbols) {
		this.symbols = symbols;
	}

	public DepartmentVO getDepartmentVO() {
		return departmentVO;
	}

	public void setDepartmentVO(DepartmentVO departmentVO) {
		this.departmentVO = departmentVO;
	}
}