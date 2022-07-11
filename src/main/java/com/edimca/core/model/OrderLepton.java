package com.edimca.core.model;

public class OrderLepton {

	public String id;
	public String code;
	public String fileName;
	public WorkList workList;

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

	public String getfileName() {
		return fileName;
	}

	public void setfileName(String fileName) {
		this.fileName = fileName;
	}

	public WorkList getWorkList() {
		return workList;
	}

	public void setWorkList(WorkList workList) {
		this.workList = workList;
	}

	public OrderLepton() {

	}

	public OrderLepton(String id, String code, String fileName, WorkList workList) {
		super();
		this.id = id;
		this.code = code;
		this.fileName = fileName;
		this.workList = workList;
	}

	@Override
	public String toString() {
		return "OrderLepton [id=" + id + ", code=" + code + ", fileName=" + fileName + ", workList="
				+ workList + "]";
	}

}
