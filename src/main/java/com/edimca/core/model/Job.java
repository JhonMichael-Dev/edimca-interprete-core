package com.edimca.core.model;

import java.util.ArrayList;

public class Job {

	public String id;
	public String code;
	public String type;
	public String toWork;
	public String qBoards;
	public String exeOrder;
	public Board board;
	public ArrayList<Piece> piece;
	public Program program;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getToWork() {
		return toWork;
	}

	public void setToWork(String toWork) {
		this.toWork = toWork;
	}

	public String getqBoards() {
		return qBoards;
	}

	public void setqBoards(String qBoards) {
		this.qBoards = qBoards;
	}

	public String getExeOrder() {
		return exeOrder;
	}

	public void setExeOrder(String exeOrder) {
		this.exeOrder = exeOrder;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public ArrayList<Piece> getPiece() {
		return piece;
	}

	public void setPiece(ArrayList<Piece> piece) {
		this.piece = piece;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public Job() {

	}

	public Job(String id, String code, String type, String toWork, String qBoards, String exeOrder, Board board,
			ArrayList<Piece> piece, Program program) {
		this.id = id;
		this.code = code;
		this.type = type;
		this.toWork = toWork;
		this.qBoards = qBoards;
		this.exeOrder = exeOrder;
		this.board = board;
		this.piece = piece;
		this.program = program;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", code=" + code + ", type=" + type + ", toWork=" + toWork + ", qBoards=" + qBoards
				+ ", exeOrder=" + exeOrder + ", board=" + board + ", piece=" + piece + ", program=" + program + "]";
	}

}
