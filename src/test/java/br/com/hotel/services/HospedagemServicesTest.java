package br.com.hotel.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.hotel.data.HospedagemVO;
import br.com.hotel.mocks.MockHospedagem;
import br.com.hotel.model.Hospedagem;
import br.com.hotel.repository.HospedagemRepository;

class HospedagemServicesTest {
	
	MockHospedagem input;
	
	@InjectMocks
	private HospedagemServices service;
	
	@Mock
	HospedagemRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		input = new MockHospedagem();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		List<Hospedagem> list = input.mockEntityList();
		when(repository.findAll()).thenReturn(list);
		
		var hospedagens = service.findAll();
		
		assertNotNull(hospedagens);
		assertEquals(14, hospedagens.size());
		
		var hospedagem1 = hospedagens.get(1);
		
		assertTrue(hospedagem1.getAdicionalVeiculo());
		assertNotNull(hospedagem1.getDataEntrada());
		assertNotNull(hospedagem1.getDataSaida());
		assertNotNull(hospedagem1.getValorEstadia());
				
		var hospedagem3 = hospedagens.get(3);
		
		assertTrue(hospedagem3.getAdicionalVeiculo());
		assertNotNull(hospedagem3.getDataEntrada());
		assertNotNull(hospedagem3.getDataSaida());
		assertNotNull(hospedagem3.getValorEstadia());
		
	}

	@Test
	void testFindById() throws Exception {
		Hospedagem hospedagem = input.mockEntity();
		hospedagem.setId(1L);
		when(repository.findById(1L)).thenReturn(Optional.of(hospedagem));
		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		//assertNotNull(result.getLinks());
		assertTrue(result.getAdicionalVeiculo());
		assertNotNull(result.getDataEntrada());
		assertNotNull(result.getDataSaida());
		assertNotNull(result.getValorEstadia());
	}

	@Test
	void testCadastraHospedagem() throws Exception {
		Hospedagem hospedagem = input.mockEntity();
		
		Hospedagem persisted = hospedagem;
		persisted.setId(1L);
		
		HospedagemVO pessoaVO = input.mockVO(0L);
		pessoaVO.setKey(1L);
		
		when(repository.save(hospedagem)).thenReturn(persisted);
		//doReturn(persisted).when(repository).save(persisted);
		HospedagemVO result = service.cadastraHospedagem(pessoaVO);
		assertTrue(result.getAdicionalVeiculo());
		assertNotNull(result.getDataEntrada());
		assertNotNull(result.getDataSaida());
		assertNotNull(result.getValorEstadia());
	}

}
