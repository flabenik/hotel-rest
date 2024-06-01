package br.com.hotel.data;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

public class HospedagemVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	@Mapping("id")
	private Long key;
	
	@Mapping("dataEntrada")
	private Date dataEntrada;
	
	@Mapping("dataSaida")
	private Date dataSaida;
	
	@Mapping("adicionalVeiculo")
	private Boolean adicionalVeiculo;
	
	public HospedagemVO() {}


	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Boolean getAdicionalVeiculo() {
		return adicionalVeiculo;
	}

	public void setAdicionalVeiculo(Boolean adicionalVeiculo) {
		this.adicionalVeiculo = adicionalVeiculo;
	}
	
	

	
	
	
	

}
