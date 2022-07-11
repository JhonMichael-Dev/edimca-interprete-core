package com.edimca.core.model;

public class Piece {

	public String n;
	public Integer id;
	public float large;
	public float width;
	public String q;
	public String qPatt;

	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getqPatt() {
		return qPatt;
	}

	public void setqPatt(String qPatt) {
		this.qPatt = qPatt;
	}

	public Piece() {

	}

	public Piece(String n, Integer id, float large, float width, String q, String qPatt) {
		this.n = n;
		this.id = id;
		this.large = large;
		this.width = width;
		this.q = q;
		this.qPatt = qPatt;
	}

	@Override
	public String toString() {
		return "Piece [n=" + n + ", id=" + id + ", large=" + large + ", width=" + width + ", q=" + q + ", qPatt="
				+ qPatt + "]";
	}

}
