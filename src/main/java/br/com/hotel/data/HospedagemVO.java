package br.com.hotel.data;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"id", "dataEntrada", "dataSaida", "adicionalVeiculo", "valorEstadia"})
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
	
	@Mapping("valorEstadia")
	private double valorEstadia;
	
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

	public double getValorEstadia() {
		return valorEstadia;
	}

	public void setValorEstadia(double valorEstadia) {
		this.valorEstadia = valorEstadia;
	}

}
