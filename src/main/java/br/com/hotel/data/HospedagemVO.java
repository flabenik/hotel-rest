package br.com.hotel.data;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

public class HospedagemVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	@Mapping("id")
	private Long key;
	
	@Mapping("dataEntrada")
	private LocalDateTime dataEntrada;
	
	@Mapping("dataSaida")
	private LocalDateTime dataSaida;
	
	@Mapping("adicionalVeiculo")
	private Boolean adicionalVeiculo;
	
	public HospedagemVO() {}


	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDateTime getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Boolean getAdicionalVeiculo() {
		return adicionalVeiculo;
	}

	public void setAdicionalVeiculo(Boolean adicionalVeiculo) {
		this.adicionalVeiculo = adicionalVeiculo;
	}
	
	

	
	
	
	

}
