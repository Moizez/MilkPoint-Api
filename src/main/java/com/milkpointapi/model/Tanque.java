package com.milkpointapi.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.milkpointapi.enums.Capacidade;
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

	@Column(name = "dep_pendentes_contador")
	private int depPendenteCount = 0;

	@Column(name = "dep_contador")
	private int depCount = 0;

	@Column(name = "ret_pendentes_contador")
	private int retPendenteCount = 0;

	@Column(name = "ret_contador")
	private int retCount = 0;

	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataCriacao;

	@ManyToOne
	public Responsavel responsavel;

	@Column
	private Capacidade capacidade;

	@Column
	private Tipo tipo;

	@Column
	private boolean status;

	@Column
	private String observacao;

	@Column
	private ZonedDateTime dataInativado;

	@ManyToOne
	public Tecnico tecnico;

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public ZonedDateTime getDataInativado() {
		return dataInativado;
	}

	public void setDataInativado(ZonedDateTime dataInativado) {
		this.dataInativado = dataInativado;
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

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
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

	public int getDepCount() {
		return depCount;
	}

	public void setDepCount(int depCount) {
		this.depCount = depCount;
	}

	public int getRetCount() {
		return retCount;
	}

	public void setRetCount(int retCount) {
		this.retCount = retCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getDepPendenteCount() {
		return depPendenteCount;
	}

	public void setDepPendenteCount(int depPendenteCount) {
		this.depPendenteCount = depPendenteCount;
	}

	public int getRetPendenteCount() {
		return retPendenteCount;
	}

	public void setRetPendenteCount(int retPendenteCount) {
		this.retPendenteCount = retPendenteCount;
	}

}
