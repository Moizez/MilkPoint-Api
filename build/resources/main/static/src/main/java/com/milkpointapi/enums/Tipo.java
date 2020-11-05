package com.milkpointapi.enums;

public enum Tipo {
	BOVINO("Bovino"), CAPRINO("Caprino");
	
	private String valor;

	Tipo (String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
