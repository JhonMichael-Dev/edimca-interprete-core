package com.edimca.core.model;

import java.util.ArrayList;

public class WorkList {

	public String id;
	public String code;
	public String released;
	public String due;
	public String materialId;
	public String comment;
	public ArrayList<Part> part;
	public ArrayList<Job> job;

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

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	public String getDue() {
		return due;
	}

	public void setDue(String due) {
		this.due = due;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ArrayList<Part> getPart() {
		return part;
	}

	public void setPart(ArrayList<Part> part) {
		this.part = part;
	}

	public ArrayList<Job> getJob() {
		return job;
	}

	public void setJob(ArrayList<Job> job) {
		this.job = job;
	}

	public WorkList() {

	}

	public WorkList(String id, String code, String released, String due, String materialId, String comment,
			ArrayList<Part> part, ArrayList<Job> job) {
		this.id = id;
		this.code = code;
		this.released = released;
		this.due = due;
		this.materialId = materialId;
		this.comment = comment;
		this.part = part;
		this.job = job;
	}

	@Override
	public String toString() {
		return "WorkList [id=" + id + ", code=" + code + ", released=" + released + ", due=" + due + ", materialId="
				+ materialId + ", comment=" + comment + ", part=" + part + ", job=" + job + "]";
	}

}
