package com.edimca.core.model;

import java.util.ArrayList;

public class Program {

	public ArrayList<Cut> cut;

	public ArrayList<Cut> getCut() {
		return cut;
	}

	public void setCut(ArrayList<Cut> cut) {
		this.cut = cut;
	}

	public Program() {

	}

	public Program(ArrayList<Cut> cut) {
		this.cut = cut;
	}

	@Override
	public String toString() {
		return "Program [cut=" + cut + "]";
	}

}
