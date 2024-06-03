package br.com.hotel.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotel.controllers.PessoaController;
import br.com.hotel.data.CheckinPessoaVO;
import br.com.hotel.data.HospedagemVO;
import br.com.hotel.data.PessoaVO;
import br.com.hotel.exception.RequiredObjectIsNullException;
import br.com.hotel.exception.ResourceNotFoundException;
import br.com.hotel.mapper.ModMapper;
import br.com.hotel.model.Hospedagem;
import br.com.hotel.model.Pessoa;
import br.com.hotel.repository.PessoaRepository;
import br.com.hotel.util.Util;

@Service
public class PessoaServices {
	
	private Logger logger = Logger.getLogger(PessoaServices.class.getName());
	
	@Autowired
	PessoaRepository repository;
	
	public List<CheckinPessoaVO> buscaTodosHospedesEHospedagens() {
		logger.info("PessoaService - buscaTodosHospedesEHospedagens");			
		List<CheckinPessoaVO> pessoas = new ArrayList<CheckinPessoaVO>();
		pessoas = ModMapper.parseListObjects(repository.findAll(), CheckinPessoaVO.class);
		pessoas.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(PessoaController.class).buscaHospedePorId(p.getKey())).withSelfRel());
				p.setValorEstadiaTotal(calculaValorEstadiaTotal(p.getHospedagens()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return pessoas;
	}
	
	public List<PessoaVO> buscaTodosHospedes() {
		logger.info("PessoaService - buscaTodosHospedes");		
		List<PessoaVO> pessoas = new ArrayList<PessoaVO>();
		pessoas = ModMapper.parseListObjects(repository.findAll(), PessoaVO.class);
		pessoas.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(PessoaController.class).buscaHospedePorId(p.getKey())).withSelfRel());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return pessoas;
	}
	
	public List<Map<String, Object>> buscaHospedesHospedagemFinalizada() {
		logger.info("PessoaService - buscaHospedesHospedagemFinalizada");	
		List<Map<String, Object>> pessoasHospedagem = new ArrayList<Map<String, Object>>();
		pessoasHospedagem = repository.buscaHospedesHospedagemFinalizada();
		return pessoasHospedagem;
	}
	
	public List<Map<String, Object>> buscaHospedesHospedagemAndamento() {
		logger.info("PessoaService - buscaHospedesHospedagemAndamento");	
		List<Map<String, Object>> pessoasHospedagem = new ArrayList<Map<String, Object>>();
		pessoasHospedagem = repository.buscaHospedesHospedagemAndamento();
		return pessoasHospedagem;
	}
	
	public PessoaVO buscaHospedePorId(Long id) throws Exception {
		logger.info("PessoaService - buscaHospedePorId");
		Pessoa entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		PessoaVO vo =  ModMapper.parseObject(entity, PessoaVO.class);
		vo.add(linkTo(methodOn(PessoaController.class).buscaHospedePorId(id)).withSelfRel());
		return vo;
	}

	public CheckinPessoaVO buscaHospedeEHospedagensPorId(Long id) throws Exception {
		logger.info("PessoaService - buscaHospedeEHospedagensPorId");
		Pessoa entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		CheckinPessoaVO vo =  ModMapper.parseObject(entity, CheckinPessoaVO.class);
		vo.setValorEstadiaTotal(calculaValorEstadiaTotal(vo.getHospedagens()));
		vo.add(linkTo(methodOn(PessoaController.class).buscaHospedeEHospedagensPorId(id)).withSelfRel());
		return vo;
	}
	
	public PessoaVO buscaHospedePorNome(String nome) throws Exception {
		logger.info("PessoaService - buscaHospedePorNome");
		Pessoa pessoa = repository.buscaHospedePorNome(nome.toUpperCase());
		PessoaVO vo = ModMapper.parseObject(pessoa, PessoaVO.class);
		vo.add(linkTo(methodOn(PessoaController.class).buscaHospedeEHospedagensPorId(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public PessoaVO buscaPorTelefone(String telefone) throws Exception {
		logger.info("PessoaService - buscaPorTelefone");
		Pessoa pessoa = repository.buscaPorTelefone(telefone);
		PessoaVO vo = ModMapper.parseObject(pessoa, PessoaVO.class);
		vo.add(linkTo(methodOn(PessoaController.class).buscaHospedeEHospedagensPorId(vo.getKey())).withSelfRel());
		return vo;
	}

	public PessoaVO buscaPorDocumento(String documento) throws Exception {
		logger.info("PessoaService - buscaPorDocumento");
		Pessoa pessoa = repository.buscaPorDocumento(documento);
		PessoaVO vo = ModMapper.parseObject(pessoa, PessoaVO.class);
		vo.add(linkTo(methodOn(PessoaController.class).buscaHospedeEHospedagensPorId(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public PessoaVO cadastraHospede(PessoaVO pessoa) throws Exception {
		logger.info("PessoaService - cadastraHospede");
		if(pessoa == null) throw new RequiredObjectIsNullException();
		
		Pessoa entity = ModMapper.parseObject(pessoa, Pessoa.class);
		entity= formataPessoa(entity);
		entity = repository.save(entity);
		PessoaVO entityVO = ModMapper.parseObject(entity, PessoaVO.class);
		entityVO.add(linkTo(methodOn(PessoaController.class).buscaHospedePorId(entityVO.getKey())).withSelfRel());
		return entityVO;
	}

	public PessoaVO atualizaHospede(PessoaVO pessoa) throws Exception {
		logger.info("PessoaService - atualizaHospede");
		if(pessoa == null) throw new RequiredObjectIsNullException();
		
		Pessoa entity = repository.findById(pessoa.getKey())
		.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID!"));
		
		
		entity.setNome(pessoa.getNome().toUpperCase());
		entity.setDocumento(Util.removeCaracteresEspeciais(pessoa.getDocumento()));
		entity.setTelefone(Util.removeCaracteresEspeciais(pessoa.getTelefone()));
		
		PessoaVO entityVO = ModMapper.parseObject(repository.save(entity), PessoaVO.class);
		entityVO.add(linkTo(methodOn(PessoaController.class).buscaHospedePorId(entityVO.getKey())).withSelfRel());
		return entityVO;
	}
	
	public void delete(Long id) {
		logger.info("PessoaService - delete");
		
		Pessoa entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID!"));
		
		repository.delete(entity);
	}
	
	public CheckinPessoaVO cadastraCheckin(CheckinPessoaVO pessoa) throws Exception {
		logger.info("PessoaService - cadastraCheckin");
		if(pessoa == null) throw new RequiredObjectIsNullException();
		Pessoa entity = ModMapper.parseObject(pessoa, Pessoa.class);
		if(entity.getId() != null ) {
			entity.getHospedagens().get(0).setPessoa(entity);
		}

		entity.getHospedagens().get(0).setPessoa(entity);
		
		entity= calculaDiaria(entity);		
		CheckinPessoaVO entityVO = ModMapper.parseObject(repository.save(entity), CheckinPessoaVO.class);
		entityVO.add(linkTo(methodOn(PessoaController.class).buscaHospedePorId(entityVO.getKey())).withSelfRel());
		return entityVO;
	}
	
	private Pessoa calculaDiaria(Pessoa entity) {
		Pessoa pessoa = entity;
		List<Hospedagem> hospedagem = entity.getHospedagens();
		
		double valor = 0;
		
		LocalDateTime date1 = hospedagem.get(0).getDataEntrada();
		LocalDateTime date2 = hospedagem.get(0).getDataSaida();
	    
	    LocalDate dataEntrada = date1.toLocalDate();
	    LocalDate dataSaida = date2.toLocalDate();
	    	  	    
	    for (LocalDate date = dataEntrada; !dataEntrada.isAfter(dataSaida) && !date.isAfter(dataSaida) ; date = date.plusDays(1)) {
	    	if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
	    		valor += 150;
	    		if (hospedagem.get(0).getAdicionalVeiculo()) {
	    	    	valor += 20;
	    	    }
	    	}else {
	    		valor += 120;
	    		if (hospedagem.get(0).getAdicionalVeiculo()) {
	    	    	valor += 15;
	    	    }
	    	}
	    }
	    //Verifica data e horário saída
	    if( date2.getHour() >= 16 & date2.getMinute() >=30 ) {
	    	if (dataSaida.getDayOfWeek() == DayOfWeek.SATURDAY || dataSaida.getDayOfWeek() == DayOfWeek.SUNDAY) {
	    		valor += 150;
	    		if (hospedagem.get(0).getAdicionalVeiculo()) {
	    	    	valor += 20;
	    	    }
	    	}else {
	    		valor += 120;
	    		if (hospedagem.get(0).getAdicionalVeiculo()) {
	    	    	valor += 15;
	    	    }
	    	}
	    }
	    
	    pessoa.getHospedagens().get(0).setValorEstadia(valor);
		return pessoa;
	}
	
	private double calculaValorEstadiaTotal(List<HospedagemVO> hospedagens) {
		double valorTotal = 0;
		for (HospedagemVO hospedagemVO : hospedagens) {
			valorTotal += hospedagemVO.getValorEstadia();
		}
		return valorTotal;
	}

	private Pessoa formataPessoa(Pessoa entity) {
		Pessoa pessoa = entity;
		pessoa.setNome(entity.getNome().toUpperCase());
		pessoa.setDocumento(Util.removeCaracteresEspeciais(entity.getDocumento()));
		pessoa.setTelefone(Util.removeCaracteresEspeciais(entity.getTelefone()));
		return pessoa;
	}


	


	
	
	

}
