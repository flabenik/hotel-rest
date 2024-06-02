package br.com.hotel.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotel.data.HospedagemVO;
import br.com.hotel.exception.RequiredObjectIsNullException;
import br.com.hotel.exception.ResourceNotFoundException;
import br.com.hotel.mapper.ModMapper;
import br.com.hotel.model.Hospedagem;
import br.com.hotel.repository.HospedagemRepository;

@Service
public class HospedagemServices {
	
	private Logger logger = Logger.getLogger(HospedagemServices.class.getName());
	
	@Autowired
	HospedagemRepository repository;
	
	public List<HospedagemVO> findAll() {
		logger.info("Finding all people");		
		List<HospedagemVO> hospedagens = new ArrayList<HospedagemVO>();
		hospedagens = ModMapper.parseListObjects(repository.findAll(), HospedagemVO.class);
		return hospedagens;
	}
	
	public HospedagemVO findById(Long id) throws Exception {
		logger.info("Finding Person");
		Hospedagem entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		HospedagemVO vo =  ModMapper.parseObject(entity, HospedagemVO.class);
		//vo.add(linkTo(methodOn(HospedagemController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public HospedagemVO cadastraHospedagem(HospedagemVO person) throws Exception {
		logger.info("Create PersonVO");
		if(person == null) throw new RequiredObjectIsNullException();
		
		Hospedagem entity = ModMapper.parseObject(person, Hospedagem.class);
		//entity= formataPessoa(entity);
		HospedagemVO entityVO = ModMapper.parseObject(repository.save(entity), HospedagemVO.class);
		//entityVO.add(linkTo(methodOn(HospedagemController.class).findById(entityVO.getKey())).withSelfRel());
		return entityVO;
	}
	

}