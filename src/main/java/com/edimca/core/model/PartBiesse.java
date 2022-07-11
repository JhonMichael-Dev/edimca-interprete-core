package com.edimca.core.model;

public class PartBiesse {

	public Integer id;
	public float L;
	public float W;
	public Integer qMin;
	public Integer Code;
	public String IDesc;
	public String IIDesc;
	public String InsLam;
	public String CabInfo;
	public String MatEdgeL;
	public String MatEdgeUp;
	public String MatEdgeR;
	public String MatEdgeLo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getL() {
		return L;
	}

	public void setL(float l) {
		L = l;
	}

	public float getW() {
		return W;
	}

	public void setW(float w) {
		W = w;
	}

	public Integer getqMin() {
		return qMin;
	}

	public void setqMin(Integer qMin) {
		this.qMin = qMin;
	}

	public Integer getCode() {
		return Code;
	}

	public void setCode(Integer code) {
		Code = code;
	}

	public String getIDesc() {
		return IDesc;
	}

	public void setIDesc(String iDesc) {
		IDesc = iDesc;
	}

	public String getIIDesc() {
		return IIDesc;
	}

	public void setIIDesc(String iIDesc) {
		IIDesc = iIDesc;
	}

	public String getInsLam() {
		return InsLam;
	}

	public void setInsLam(String insLam) {
		InsLam = insLam;
	}

	public String getCabInfo() {
		return CabInfo;
	}

	public void setCabInfo(String cabInfo) {
		CabInfo = cabInfo;
	}

	public String getMatEdgeL() {
		return MatEdgeL;
	}

	public void setMatEdgeL(String matEdgeL) {
		MatEdgeL = matEdgeL;
	}

	public String getMatEdgeUp() {
		return MatEdgeUp;
	}

	public void setMatEdgeUp(String matEdgeUp) {
		MatEdgeUp = matEdgeUp;
	}

	public String getMatEdgeR() {
		return MatEdgeR;
	}

	public void setMatEdgeR(String matEdgeR) {
		MatEdgeR = matEdgeR;
	}

	public String getMatEdgeLo() {
		return MatEdgeLo;
	}

	public void setMatEdgeLo(String matEdgeLo) {
		MatEdgeLo = matEdgeLo;
	}

	public PartBiesse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PartBiesse(Integer id, float l, float w, Integer qMin, Integer code, String iDesc, String iIDesc,
			String insLam, String cabInfo, String matEdgeL, String matEdgeUp, String matEdgeR, String matEdgeLo) {
		super();
		this.id = id;
		L = l;
		W = w;
		this.qMin = qMin;
		Code = code;
		IDesc = iDesc;
		IIDesc = iIDesc;
		InsLam = insLam;
		CabInfo = cabInfo;
		MatEdgeL = matEdgeL;
		MatEdgeUp = matEdgeUp;
		MatEdgeR = matEdgeR;
		MatEdgeLo = matEdgeLo;
	}

	@Override
	public String toString() {
		return "PartBiesse [id=" + id + ", L=" + L + ", W=" + W + ", qMin=" + qMin + ", Code=" + Code + ", IDesc="
				+ IDesc + ", IIDesc=" + IIDesc + ", InsLam=" + InsLam + ", CabInfo=" + CabInfo + ", MatEdgeL="
				+ MatEdgeL + ", MatEdgeUp=" + MatEdgeUp + ", MatEdgeR=" + MatEdgeR + ", MatEdgeLo=" + MatEdgeLo + "]";
	}

}
