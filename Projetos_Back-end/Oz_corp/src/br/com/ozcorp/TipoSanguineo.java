package br.com.ozcorp;

public enum TipoSanguineo {
	A_M("A+"), A_MN("A-"), B_M("B+"), B_MN("B-"), AB_MN("AB-"), O_M("O+"), O_MN("O-"), AB_M("AB+");

	public String tipoSanguineo;

	TipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}
}
