package com.edimca.core.model;

public class OrderBiesse {

	public String id;
	public String code;
	public WorkListBiesse workList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public WorkListBiesse getWorkList() {
		return workList;
	}

	public void setWorkList(WorkListBiesse workList) {
		this.workList = workList;
	}

	public OrderBiesse() {

	}

	public OrderBiesse(String id, String code, WorkListBiesse workList) {
		this.id = id;
		this.code = code;
		this.workList = workList;
	}

	@Override
	public String toString() {
		return "OrderBiesse [id=" + id + ", code=" + code + ", workList=" + workList + "]";
	}

}
