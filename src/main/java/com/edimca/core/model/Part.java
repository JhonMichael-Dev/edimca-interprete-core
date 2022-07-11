package com.edimca.core.model;

public class Part {

	public Integer id;
	public Integer qMin;
	public Integer code;
	public String info1; // Informacion 1
	public String info2; // Informacion 2
	public String ean13; // Codigo barras
	public String detalle;
	public String material;

	// CORTE
	public boolean veta;
	public Integer nPiezas;
	public float largo;
	public float ancho;

	// LAMINADO (lam)
	public String lamA1;
	public String lamB2;
	public String lamA2;
	public String lamB1;

	// CANAL (can)
	public float esp; // Espesor (Ancho Canal)
	public String canCara; // (Prin/Sec) Principal o Secundaria
	public float dbl; // Distancia del borde lateral
	public float canPrf; // Profundidad
	public String canA1;
	public String canB2;
	public String canA2;
	public String canB1;

	// PERFORACION (perf)
	public float dmt; // Diametro (Minibisagras 25; Bisagras 35)
	public String perfCara; // (Prin/Sec) Principal o Secundaria
	public float perfPrf; // Profundidad
	public float dbs; // Distancia al borde superior
	public float dbi; // Distancia al borde inferior
	public String perfA1;
	public String perfB2;
	public String perfA2;
	public String perfB1;

	// Sistema32 (sis) Distancia requerida por cada 5 perforaciones
	public String sisCara; // (Prin/Sec) Principal o Secundaria
	public String sisA;
	public String sisB;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getqMin() {
		return qMin;
	}

	public void setqMin(Integer qMin) {
		this.qMin = qMin;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getInfo1() {
		return info1;
	}

	public void setInfo1(String info1) {
		this.info1 = info1;
	}

	public String getInfo2() {
		return info2;
	}

	public void setInfo2(String info2) {
		this.info2 = info2;
	}

	public String getEan13() {
		return ean13;
	}

	public void setEan13(String ean13) {
		this.ean13 = ean13;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public boolean isVeta() {
		return veta;
	}

	public void setVeta(boolean veta) {
		this.veta = veta;
	}

	public Integer getnPiezas() {
		return nPiezas;
	}

	public void setnPiezas(Integer nPiezas) {
		this.nPiezas = nPiezas;
	}

	public float getLargo() {
		return largo;
	}

	public void setLargo(float largo) {
		this.largo = largo;
	}

	public float getAncho() {
		return ancho;
	}

	public void setAncho(float ancho) {
		this.ancho = ancho;
	}

	public String getLamA1() {
		return lamA1;
	}

	public void setLamA1(String lamA1) {
		this.lamA1 = lamA1;
	}

	public String getLamB2() {
		return lamB2;
	}

	public void setLamB2(String lamB2) {
		this.lamB2 = lamB2;
	}

	public String getLamA2() {
		return lamA2;
	}

	public void setLamA2(String lamA2) {
		this.lamA2 = lamA2;
	}

	public String getLamB1() {
		return lamB1;
	}

	public void setLamB1(String lamB1) {
		this.lamB1 = lamB1;
	}

	public float getEsp() {
		return esp;
	}

	public void setEsp(float esp) {
		this.esp = esp;
	}

	public String getCanCara() {
		return canCara;
	}

	public void setCanCara(String canCara) {
		this.canCara = canCara;
	}

	public float getDbl() {
		return dbl;
	}

	public void setDbl(float dbl) {
		this.dbl = dbl;
	}

	public float getCanPrf() {
		return canPrf;
	}

	public void setCanPrf(float canPrf) {
		this.canPrf = canPrf;
	}

	public String getCanA1() {
		return canA1;
	}

	public void setCanA1(String canA1) {
		this.canA1 = canA1;
	}

	public String getCanB2() {
		return canB2;
	}

	public void setCanB2(String canB2) {
		this.canB2 = canB2;
	}

	public String getCanA2() {
		return canA2;
	}

	public void setCanA2(String canA2) {
		this.canA2 = canA2;
	}

	public String getCanB1() {
		return canB1;
	}

	public void setCanB1(String canB1) {
		this.canB1 = canB1;
	}

	public float getDmt() {
		return dmt;
	}

	public void setDmt(float dmt) {
		this.dmt = dmt;
	}

	public String getPerfCara() {
		return perfCara;
	}

	public void setPerfCara(String perfCara) {
		this.perfCara = perfCara;
	}

	public float getPerfPrf() {
		return perfPrf;
	}

	public void setPerfPrf(float perfPrf) {
		this.perfPrf = perfPrf;
	}

	public float getDbs() {
		return dbs;
	}

	public void setDbs(float dbs) {
		this.dbs = dbs;
	}

	public float getDbi() {
		return dbi;
	}

	public void setDbi(float dbi) {
		this.dbi = dbi;
	}

	public String getPerfA1() {
		return perfA1;
	}

	public void setPerfA1(String perfA1) {
		this.perfA1 = perfA1;
	}

	public String getPerfB2() {
		return perfB2;
	}

	public void setPerfB2(String perfB2) {
		this.perfB2 = perfB2;
	}

	public String getPerfA2() {
		return perfA2;
	}

	public void setPerfA2(String perfA2) {
		this.perfA2 = perfA2;
	}

	public String getPerfB1() {
		return perfB1;
	}

	public void setPerfB1(String perfB1) {
		this.perfB1 = perfB1;
	}

	public String getSisCara() {
		return sisCara;
	}

	public void setSisCara(String sisCara) {
		this.sisCara = sisCara;
	}

	public String getSisA() {
		return sisA;
	}

	public void setSisA(String sisA) {
		this.sisA = sisA;
	}

	public String getSisB() {
		return sisB;
	}

	public void setSisB(String sisB) {
		this.sisB = sisB;
	}

	public Part() {

	}

	public Part(Integer id, Integer qMin, Integer code, String info1, String info2, String ean13, String detalle,
			String material, boolean veta, Integer nPiezas, float largo, float ancho, String lamA1, String lamB2,
			String lamA2, String lamB1, float esp, String canCara, float dbl, float canPrf, String canA1, String canB2,
			String canA2, String canB1, float dmt, String perfCara, float perfPrf, float dbs, float dbi, String perfA1,
			String perfB2, String perfA2, String perfB1, String sisCara, String sisA, String sisB) {
		this.id = id;
		this.qMin = qMin;
		this.code = code;
		this.info1 = info1;
		this.info2 = info2;
		this.ean13 = ean13;
		this.detalle = detalle;
		this.material = material;
		this.veta = veta;
		this.nPiezas = nPiezas;
		this.largo = largo;
		this.ancho = ancho;
		this.lamA1 = lamA1;
		this.lamB2 = lamB2;
		this.lamA2 = lamA2;
		this.lamB1 = lamB1;
		this.esp = esp;
		this.canCara = canCara;
		this.dbl = dbl;
		this.canPrf = canPrf;
		this.canA1 = canA1;
		this.canB2 = canB2;
		this.canA2 = canA2;
		this.canB1 = canB1;
		this.dmt = dmt;
		this.perfCara = perfCara;
		this.perfPrf = perfPrf;
		this.dbs = dbs;
		this.dbi = dbi;
		this.perfA1 = perfA1;
		this.perfB2 = perfB2;
		this.perfA2 = perfA2;
		this.perfB1 = perfB1;
		this.sisCara = sisCara;
		this.sisA = sisA;
		this.sisB = sisB;
	}

	@Override
	public String toString() {
		return "Part [id=" + id + ", qMin=" + qMin + ", code=" + code + ", info1=" + info1 + ", info2=" + info2
				+ ", ean13=" + ean13 + ", detalle=" + detalle + ", material=" + material + ", veta=" + veta
				+ ", nPiezas=" + nPiezas + ", largo=" + largo + ", ancho=" + ancho + ", lamA1=" + lamA1 + ", lamB2="
				+ lamB2 + ", lamA2=" + lamA2 + ", lamB1=" + lamB1 + ", esp=" + esp + ", canCara=" + canCara + ", dbl="
				+ dbl + ", canPrf=" + canPrf + ", canA1=" + canA1 + ", canB2=" + canB2 + ", canA2=" + canA2 + ", canB1="
				+ canB1 + ", dmt=" + dmt + ", perfCara=" + perfCara + ", perfPrf=" + perfPrf + ", dbs=" + dbs + ", dbi="
				+ dbi + ", perfA1=" + perfA1 + ", perfB2=" + perfB2 + ", perfA2=" + perfA2 + ", perfB1=" + perfB1
				+ ", sisCara=" + sisCara + ", sisA=" + sisA + ", sisB=" + sisB + "]";
	}

}
