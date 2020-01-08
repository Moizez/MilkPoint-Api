package com.milkpointapi.enums;

public enum Capacidade {
	MIL("1000 Litros"), DOISMIL("2000 Litros"), TRESMIL("3000 Litros"), QUATROMIL("4000 Litros"),
	QUATROMILEQUINHENTOS("4500 Litros");

	private String descricao;

	Capacidade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
