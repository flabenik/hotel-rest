package br.com.hotel.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotel.controllers.PessoaController;
import br.com.hotel.data.PessoaVO;
import br.com.hotel.exception.RequiredObjectIsNullException;
import br.com.hotel.exception.ResourceNotFoundException;
import br.com.hotel.mapper.ModMapper;
import br.com.hotel.model.Pessoa;
import br.com.hotel.repository.PessoaRepository;
import br.com.hotel.util.Util;

@Service
public class PessoaServices {
	
	private Logger logger = Logger.getLogger(PessoaServices.class.getName());
	
	@Autowired
	PessoaRepository repository;
	
	public List<PessoaVO> findAll() {
		logger.info("Finding all people");		
		List<PessoaVO> persons = new ArrayList<PessoaVO>();
		persons = ModMapper.parseListObjects(repository.findAll(), PessoaVO.class);
		persons.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(PessoaController.class).findById(p.getKey())).withSelfRel());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return persons;
	}
	

	public PessoaVO findById(Long id) throws Exception {
		logger.info("Finding Person");
		Pessoa entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		PessoaVO vo =  ModMapper.parseObject(entity, PessoaVO.class);
		vo.add(linkTo(methodOn(PessoaController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public PessoaVO findPersonByName(String firstName) {
		Pessoa person = repository.findPersonByName(firstName.toUpperCase());
		PessoaVO vo = ModMapper.parseObject(person, PessoaVO.class);
		return vo;
	}
	
	public PessoaVO buscaPorTelefone(String telefone) {
		Pessoa person = repository.buscaPorTelefone(telefone);
		PessoaVO vo = ModMapper.parseObject(person, PessoaVO.class);
		return vo;
	}
	

	public PessoaVO buscaPorDocumento(String documento) {
		Pessoa person = repository.buscaPorDocumento(documento);
		PessoaVO vo = ModMapper.parseObject(person, PessoaVO.class);
		return vo;
	}
	
	public PessoaVO create(PessoaVO person) throws Exception {
		logger.info("Create PersonVO");
		if(person == null) throw new RequiredObjectIsNullException();
		
		Pessoa entity = ModMapper.parseObject(person, Pessoa.class);
		entity= formataPessoa(entity);
		PessoaVO entityVO = ModMapper.parseObject(repository.save(entity), PessoaVO.class);
		entityVO.add(linkTo(methodOn(PessoaController.class).findById(entityVO.getKey())).withSelfRel());
		return entityVO;
	}

	public PessoaVO update(PessoaVO person) throws Exception {
		logger.info("Update PersonVO");
		if(person == null) throw new RequiredObjectIsNullException();
		
		Pessoa entity = repository.findById(person.getKey())
		.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID!"));
		
		
		entity.setNome(person.getNome().toUpperCase());
		entity.setDocumento(Util.removeCaracteresEspeciais(person.getDocumento()));
		entity.setTelefone(Util.removeCaracteresEspeciais(person.getTelefone()));
		
		PessoaVO entityVO = ModMapper.parseObject(repository.save(entity), PessoaVO.class);
		entityVO.add(linkTo(methodOn(PessoaController.class).findById(entityVO.getKey())).withSelfRel());
		return entityVO;
	}
	
	public void delete(Long id) {
		logger.info("Delete PersonVO");
		
		Pessoa entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID!"));
		
		repository.delete(entity);
	}
	
	private Pessoa formataPessoa(Pessoa entity) {
		Pessoa person = entity;
		person.setNome(entity.getNome().toUpperCase());
		person.setDocumento(Util.removeCaracteresEspeciais(entity.getDocumento()));
		person.setTelefone(Util.removeCaracteresEspeciais(entity.getTelefone()));
		return person;
	}


	
	
	

}
