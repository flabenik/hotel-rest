package br.com.hotel.mocks;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.hotel.data.HospedagemVO;
import br.com.hotel.model.Hospedagem;
import br.com.hotel.services.PessoaServices;

public class MockHospedagem {
	
	PessoaServices service;
	
	public Hospedagem mockEntity() {
		return mockEntity(0L);
	}
	
	public HospedagemVO mockEntityVO() {
		return mockVO(0L);
	}
	
	public List<Hospedagem> mockEntityList() {
        List<Hospedagem> pessoa = new ArrayList<Hospedagem>();
        for (long i = 0; i < 14; i++) {
        	pessoa.add(mockEntity(i));
        }
        return pessoa;
    }

    public List<HospedagemVO> mockVOList() {
        List<HospedagemVO> pessoas = new ArrayList<>();
        for (long i = 0; i < 14; i++) {
        	pessoas.add(mockVO(i));
        }
        return pessoas;
    }
    
	
	public Hospedagem mockEntity(Long number) {
		Hospedagem hospedagem = new Hospedagem();
		hospedagem.setId(number);
		hospedagem.setDataEntrada(LocalDateTime.now() );
		hospedagem.setDataSaida(LocalDateTime.now().plusDays(5));
		hospedagem.setAdicionalVeiculo(true);
		hospedagem.setValorEstadia(calculaDiaria(hospedagem.getDataEntrada(),hospedagem.getDataSaida()));
        return hospedagem;
    }
	
	public HospedagemVO mockVO(Long number) {
		HospedagemVO hospedagem = new HospedagemVO();
		hospedagem.setKey(number);
		hospedagem.setDataEntrada(LocalDateTime.now() );
		hospedagem.setDataSaida(LocalDateTime.now().plusDays(5));
		hospedagem.setAdicionalVeiculo(true);
		hospedagem.setValorEstadia(calculaDiaria(hospedagem.getDataEntrada(),hospedagem.getDataSaida()));
        return hospedagem;
    }
	
	private double calculaDiaria(LocalDateTime dataEntradaIN, LocalDateTime dataSaidaIN) {
		
		double valor = 0;
		
	    
	    LocalDate dataEntrada = dataEntradaIN.toLocalDate();
	    LocalDate dataSaida = dataSaidaIN.toLocalDate();
	    	  	    
	    for (LocalDate date = dataEntrada; !dataEntrada.isAfter(dataSaida) && !date.isAfter(dataSaida) ; date = date.plusDays(1)) {
	    	if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
	    		valor += 150;
	    		if (true) {
	    	    	valor += 20;
	    	    }
	    	}else {
	    		valor += 120;
	    		if (true) {
	    	    	valor += 15;
	    	    }
	    	}
	    }
	    //Verifica data e horário saída
	    if( dataSaidaIN.getHour() >= 16 & dataSaidaIN.getMinute() >=30 ) {
	    	if (dataSaida.getDayOfWeek() == DayOfWeek.SATURDAY || dataSaida.getDayOfWeek() == DayOfWeek.SUNDAY) {
	    		valor += 150;
	    		if (true) {
	    	    	valor += 20;
	    	    }
	    	}else {
	    		valor += 120;
	    		if (true) {
	    	    	valor += 15;
	    	    }
	    	}
	    }
	    return valor;
	    
		}
	}
