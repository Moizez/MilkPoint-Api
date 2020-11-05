package com.milkpointapi.model;

public class Notificacao {
	
	private float quantidade = 0;
	private String tipo = "";
	private String solicitante = "";
	private String tanque = "";
	private boolean confirmacao = false;
	private boolean excluido = false;

	public float getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getTanque() {
		return tanque;
	}

	public void setTanque(String tanque) {
		this.tanque = tanque;
	}

	public boolean getConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(boolean confirmacao) {
		this.confirmacao = confirmacao;
	}

	public boolean getExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}
}
