package com.milkpointapi.enums;

public enum Tipo {
	bovino("Bovino"), caprino("Caprino");
	
	private String valor;

	Tipo (String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
