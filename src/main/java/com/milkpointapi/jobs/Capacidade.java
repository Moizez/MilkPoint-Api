package com.milkpointapi.jobs;

public enum Capacidade {
	ZERO("0"), MIL("1000"), DOISMIL("2000"), TRESMIL("3000"), QUATROMIL("4000"), QUATROMILEQUINHENTOS("4500");

	private String descricao;

	Capacidade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
