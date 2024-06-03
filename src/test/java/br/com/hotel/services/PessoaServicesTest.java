package br.com.hotel.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import br.com.hotel.data.PessoaVO;
import br.com.hotel.exception.RequiredObjectIsNullException;
import br.com.hotel.mocks.MockPessoa;
import br.com.hotel.model.Pessoa;
import br.com.hotel.repository.PessoaRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PessoaServicesTest {
	
	MockPessoa input;
	
	@InjectMocks
	private PessoaServices service;
	
	@Mock
	PessoaRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		input = new MockPessoa();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testBuscaTodosHospedesEHospedagens() {
		List<Pessoa> list = input.mockEntityList();
		
		when(repository.findAll()).thenReturn(list);
		
		var pessoas = service.buscaTodosHospedesEHospedagens();
		
		assertNotNull(pessoas);
		assertEquals(14, pessoas.size());
		
		var pessoa1 = pessoas.get(1);
		
		assertNotNull(pessoa1);
		assertNotNull(pessoa1.getKey());
		assertNotNull(pessoa1.getLinks());
		
		//assertTrue(pessoa1.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Nome Teste1", pessoa1.getNome());
		assertEquals("Documento1", pessoa1.getDocumento());
		assertEquals("Telefone1", pessoa1.getTelefone());
		
		var pessoa3 = pessoas.get(3);
		
		assertNotNull(pessoa3);
		assertNotNull(pessoa3.getKey());
		assertNotNull(pessoa3.getLinks());
		
		//assertTrue(pessoa1.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Nome Teste3", pessoa3.getNome());
		assertEquals("Documento3", pessoa3.getDocumento());
		assertEquals("Telefone3", pessoa3.getTelefone());
		
	}

	@Test
	void testBuscaTodosHospedes() {
		List<Pessoa> list = input.mockEntityList();
		when(repository.findAll()).thenReturn(list);
		
		var pessoas = service.buscaTodosHospedes();
		
		assertNotNull(pessoas);
		assertEquals(14, pessoas.size());
		
		var pessoa1 = pessoas.get(1);
		
		assertNotNull(pessoa1);
		assertNotNull(pessoa1.getKey());
		assertNotNull(pessoa1.getLinks());
		
		//assertTrue(pessoa1.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Nome Teste1", pessoa1.getNome());
		assertEquals("Documento1", pessoa1.getDocumento());
		assertEquals("Telefone1", pessoa1.getTelefone());
		
		var pessoa3 = pessoas.get(3);
		
		assertNotNull(pessoa3);
		assertNotNull(pessoa3.getKey());
		assertNotNull(pessoa3.getLinks());
		
		//assertTrue(pessoa1.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Nome Teste3", pessoa3.getNome());
		assertEquals("Documento3", pessoa3.getDocumento());
		assertEquals("Telefone3", pessoa3.getTelefone());
		
	}

	@Test
	void testBuscaHospedesHospedagemFinalizada() {
		List<Map<String, Object>> list = new ArrayList<>();
		when(repository.buscaHospedesHospedagemFinalizada()).thenReturn(list);
		
		var result = service.buscaHospedesHospedagemFinalizada();
		
		assertNotNull(result);
	}

	@Test
	void testBuscaHospedesHospedagemAndamento() {
		List<Map<String, Object>> list = new ArrayList<>();
		when(repository.buscaHospedesHospedagemAndamento()).thenReturn(list);
		
		var result = service.buscaHospedesHospedagemFinalizada();
		
		assertNotNull(result);
	}

	@Test
	void testBuscaHospedePorId() throws Exception {
		Pessoa pessoa = input.mockEntity();
		pessoa.setId(1L);
		when(repository.findById(1L)).thenReturn(Optional.of(pessoa));
		var result = service.buscaHospedePorId(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		//assertNotNull(result.getLinks());
		assertEquals("Nome Teste0", result.getNome());
		assertEquals("Documento0", result.getDocumento());
		assertEquals("Telefone0", result.getTelefone());
	}

	@Test
	void testBuscaHospedeEHospedagensPorId() throws Exception {
		Pessoa pessoa = input.mockEntity();
		pessoa.setId(1L);
		when(repository.findById(1L)).thenReturn(Optional.of(pessoa));
		var result = service.buscaHospedeEHospedagensPorId(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		//assertNotNull(result.getLinks());
		assertEquals("Nome Teste0", result.getNome());
		assertEquals("Documento0", result.getDocumento());
		assertEquals("Telefone0", result.getTelefone());
		assertNotNull(result.getHospedagens());
	}

	@Test
	void testBuscaHospedePorNome() {
		Pessoa pessoa = input.mockEntity();
		pessoa.setId(1L);
		when(repository.buscaHospedePorNome("NOME")).thenReturn(pessoa);
		var result = service.buscaHospedePorNome("NOME");
		assertNotNull(result);
		assertNotNull(result.getKey());
		//assertNotNull(result.getLinks());
		assertEquals("Nome Teste0", result.getNome());
		assertEquals("Documento0", result.getDocumento());
		assertEquals("Telefone0", result.getTelefone());
	}

	@Test
	void testBuscaPorTelefone() {
		Pessoa pessoa = input.mockEntity();
		pessoa.setId(1L);
		when(repository.buscaPorTelefone("Telefone0")).thenReturn(pessoa);
		var result = service.buscaPorTelefone("Telefone0");
		assertNotNull(result);
		assertNotNull(result.getKey());
		//assertNotNull(result.getLinks());
		assertEquals("Nome Teste0", result.getNome());
		assertEquals("Documento0", result.getDocumento());
		assertEquals("Telefone0", result.getTelefone());
	}

	@Test
	void testBuscaPorDocumento() {
		Pessoa pessoa = input.mockEntity();
		pessoa.setId(1L);
		when(repository.buscaPorDocumento("Documento0")).thenReturn(pessoa);
		var result = service.buscaPorDocumento("Documento0");
		assertNotNull(result);
		assertNotNull(result.getKey());
		//assertNotNull(result.getLinks());
		assertEquals("Nome Teste0", result.getNome());
		assertEquals("Documento0", result.getDocumento());
		assertEquals("Telefone0", result.getTelefone());
	}

	@Test
	void testCadastraHospede() throws Exception {
		Pessoa pessoa = input.mockEntity();
		pessoa.setId(1L);
		Pessoa persisted = pessoa;
		persisted.setId(1L);
		
		PessoaVO pessoaVO = input.mockVO(0L);
		pessoaVO.setKey(1L);
		
		when(repository.save(pessoa)).thenReturn(persisted);
		//doReturn(persisted).when(repository).save(persisted);
		PessoaVO result = service.cadastraHospede(pessoaVO);
		assertNotNull(result);
		assertNotNull(result.getKey());
		//assertNotNull(result.getLinks());
		assertEquals("NOME TESTE0", result.getNome());
		assertEquals("Documento00", result.getDocumento());
		assertEquals("Telefone00", result.getTelefone());
	}
	
	@Test
	void testCadastraHospedeNull() throws Exception {
		
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.cadastraHospede(null);
		});
		
		String expectedMessage = "Não é permitido objeto nulo!";
		String actualdMessage = exception.getMessage();
		
		assertTrue(actualdMessage.contains(expectedMessage));
		//assertNotNull(result.getLinks());
		
	}

	@Test
	void testAtualizaHospede() throws Exception {
		Pessoa pessoa = input.mockEntity();
		pessoa.setId(1L);
		Pessoa persisted = pessoa;
		persisted.setId(1L);
		
		PessoaVO pessoaVO = input.mockVO(0L);
		pessoaVO.setKey(1L);
		when(repository.findById(1L)).thenReturn(Optional.of(pessoa));
		when(repository.save(pessoa)).thenReturn(persisted);
		PessoaVO result = service.atualizaHospede(pessoaVO);
		assertNotNull(result);
		assertNotNull(result.getKey());
		//assertNotNull(result.getLinks());
		assertEquals("NOME TESTE0", result.getNome());
		assertEquals("Documento00", result.getDocumento());
		assertEquals("Telefone00", result.getTelefone());
	}
	
	@Test
	void testAtualizaHospedeNull() throws Exception {
		
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.cadastraHospede(null);
		});
		
		String expectedMessage = "Não é permitido objeto nulo!";
		String actualdMessage = exception.getMessage();
		
		assertTrue(actualdMessage.contains(expectedMessage));
		//assertNotNull(result.getLinks());
		
	}

	@Test
	void testDelete() throws Exception {
		Pessoa pessoa = input.mockEntity();
		pessoa.setId(1L);
		when(repository.findById(1L)).thenReturn(Optional.of(pessoa));
		service.delete(1L);
	}

	@Test
	void testCadastraCheckin() {
		fail("Not yet implemented");
	}

}
