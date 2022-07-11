package com.edimca.core.model;

public class Cut {

	public Integer id;
	public String code;
	public float large;
	public String rep;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public float getLarge() {
		return large;
	}

	public void setLarge(float large) {
		this.large = large;
	}

	public String getRep() {
		return rep;
	}

	public void setRep(String rep) {
		this.rep = rep;
	}

	public Cut() {

	}

	public Cut(Integer id, String code, float large, String rep) {
		this.id = id;
		this.code = code;
		this.large = large;
		this.rep = rep;
	}

	@Override
	public String toString() {
		return "Cut [id=" + id + ", code=" + code + ", large=" + large + ", rep=" + rep + "]";
	}

}
