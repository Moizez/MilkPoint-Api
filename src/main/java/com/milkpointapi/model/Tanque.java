package com.milkpointapi.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milkpointapi.jobs.Capacidade;
import com.milkpointapi.jobs.Status;

@Entity
public class Tanque implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	@Column(nullable = false, unique = true, length = 100)
	@NotBlank(message = "Nome é uma informação obrigatória.")
	private String nome;
	
	@Column
	private String descricao;

	@Column
	private String localizacao;

	@Column
	private float qtdAtual = 0;

	@Column
	private float qtdRestante;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCriacao;
	
	@Column
	private Capacidade capacidade = Capacidade.ZERO;

	@ManyToOne
	public Responsavel responsavel;

	@Column
	private Status status = Status.INATIVO;

	@JsonIgnore
	@OneToMany(mappedBy = "tanque")
	private List<Retirada> retiradas;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tanque")
	private List<Deposito> depositos;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public Capacidade getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Capacidade capacidade) {
		this.capacidade = capacidade;
	}

	public float getQtdAtual() {
		return qtdAtual;
	}

	public void setQtdAtual(float f) {
		this.qtdAtual = f;
	}

	public float getQtdRestante() {
		return qtdRestante;
	}

	public void setQtdRestante(float qtdRestante) {
		this.qtdRestante = qtdRestante;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public List<Deposito> getDepositos() {
		return depositos;
	}

	public void setDepositos(List<Deposito> depositos) {
		this.depositos = depositos;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	
	public List<Retirada> getRetiradas() {
		return retiradas;
	}

	public void setRetiradas(List<Retirada> retiradas) {
		this.retiradas = retiradas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
