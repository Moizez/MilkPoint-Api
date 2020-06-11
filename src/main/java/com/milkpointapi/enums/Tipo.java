package com.milkpointapi.enums;

public enum Tipo {
	BOVINO("bovino"), CAPRINO("caprino");
	
	private String valor;

	Tipo (String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
