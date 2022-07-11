package com.edimca.core.model;

public class Board {

	public Integer id;
	public String code;
	public float large;
	public float width;
	public float thickness;
	public String matNo;
	public String matCode;
	public String grain;

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

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getThickness() {
		return thickness;
	}

	public void setThickness(float thickness) {
		this.thickness = thickness;
	}

	public String getMatNo() {
		return matNo;
	}

	public void setMatNo(String matNo) {
		this.matNo = matNo;
	}

	public String getMatCode() {
		return matCode;
	}

	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}

	public String getGrain() {
		return grain;
	}

	public void setGrain(String grain) {
		this.grain = grain;
	}

	public Board() {

	}

	public Board(Integer id, String code, float large, float width, float thickness, String matNo, String matCode,
			String grain) {
		this.id = id;
		this.code = code;
		this.large = large;
		this.width = width;
		this.thickness = thickness;
		this.matNo = matNo;
		this.matCode = matCode;
		this.grain = grain;
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", code=" + code + ", large=" + large + ", width=" + width + ", thickness="
				+ thickness + ", matNo=" + matNo + ", matCode=" + matCode + ", grain=" + grain + "]";
	}

}
