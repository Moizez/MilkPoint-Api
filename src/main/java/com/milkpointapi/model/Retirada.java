package com.milkpointapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Retirada implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	@Column
	private float quantidade;

	@ManyToOne
	@JoinColumn(name = "retirada_laticinio")
	private Laticinio laticinio;

	@ManyToOne
	@JoinColumn(name = "retirada_tanque")
	private Tanque tanque;

	@Column
	private boolean confirmacao = false;

	@Column
	private boolean excluido = false;

	@Column
	private String efetuou;

	@Column
	private String dataNow;

	@Column
	private String horaNow;

	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	public Laticinio getLaticinio() {
		return laticinio;
	}

	public void setLaticinio(Laticinio laticinio) {
		this.laticinio = laticinio;
	}

	public Tanque getTanque() {
		return tanque;
	}

	public void setTanque(Tanque tanque) {
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

	public String getEfetuou() {
		return efetuou;
	}

	public void setEfetuou(String efetuou) {
		this.efetuou = efetuou;
	}

	public String getDataNow() {
		return dataNow;
	}

	public void setDataNow(String dataNow) {
		this.dataNow = dataNow;
	}

	public String getHoraNow() {
		return horaNow;
	}

	public void setHoraNow(String horaNow) {
		this.horaNow = horaNow;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
