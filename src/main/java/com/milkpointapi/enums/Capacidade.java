package com.milkpointapi.enums;

public enum Capacidade {
	MIL("1000"), DOISMIL("2000"), TRESMIL("3000"), QUATROMIL("4000"),
	QUATROMILEQUINHENTOS("4500");

	private String valor;

	Capacidade(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public String getDescricao() {
		return valor + " Litros";
	}
	
	@Override
	public String toString() {
		return valor;
	}
	
}
