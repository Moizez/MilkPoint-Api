package com.milkpointapi.model;

import java.io.Serializable;
import java.util.Date;
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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.milkpointapi.enums.Capacidade;
import com.milkpointapi.enums.Status;
import com.milkpointapi.enums.Tipo;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
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
	private String complemento;

	@Column
	private String cep;

	@Column
	private String logradouro;

	@Column
	private String bairro;

	@Column
	private String localidade;

	@Column
	private double latitude = 0;

	@Column
	private double longitude = 0;

	@Column
	private String uf;

	@Column
	private float qtdAtual = 0;

	@Column
	private String comunidade;

	@Column
	private float qtdRestante;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataCriacao;

	@ManyToOne
	public Responsavel responsavel;

	@ManyToOne
	public Tecnico tecnico;

	@Column
	private Capacidade capacidade;

	@Column
	private Tipo tipo = Tipo.BOVINO;

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

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
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

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getComunidade() {
		return comunidade;
	}

	public void setComunidade(String comunidade) {
		this.comunidade = comunidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public List<Deposito> getDepositos() {
		return depositos;
	}

	public void setDepositos(List<Deposito> depositos) {
		this.depositos = depositos;
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
