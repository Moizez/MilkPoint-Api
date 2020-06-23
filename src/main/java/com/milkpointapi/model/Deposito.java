package com.milkpointapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Deposito implements Serializable {

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
	@JoinColumn(name = "deposito_produtor")
	private Produtor produtor;

	@ManyToOne
	@JoinColumn(name = "deposito_tanque")
	private Tanque tanque;

	@Column
	private boolean confirmacao = false;

	@Column
	private boolean excluido = false;

	@DateTimeFormat(pattern = "dd/MM/uuuu HH:mm")
	private Date novaData;

	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	public Produtor getProdutor() {
		return produtor;
	}

	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
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

	public Date getNovaData() {
		return novaData;
	}

	public void setNovaData(Date novaData) {
		this.novaData = novaData;
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
