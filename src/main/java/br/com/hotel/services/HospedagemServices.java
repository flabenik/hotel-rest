package br.com.hotel.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotel.controllers.HospedagemController;
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
		logger.info("Retorna todas as hospedagens");		
		List<HospedagemVO> hospedagens = new ArrayList<HospedagemVO>();
		hospedagens = ModMapper.parseListObjects(repository.findAll(), HospedagemVO.class);
		return hospedagens;
	}
	
	public HospedagemVO findById(Long id) throws Exception {
		logger.info("Encontra Hospedagem por ID");
		Hospedagem entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		HospedagemVO vo =  ModMapper.parseObject(entity, HospedagemVO.class);
		return vo;
	}
	
	public HospedagemVO cadastraHospedagem(HospedagemVO hospedagem) throws Exception {
		logger.info("Cadastra Hospedagem");
		if(hospedagem == null) throw new RequiredObjectIsNullException();
		
		Hospedagem entity = ModMapper.parseObject(hospedagem, Hospedagem.class);
		HospedagemVO entityVO = ModMapper.parseObject(repository.save(entity), HospedagemVO.class);
		return entityVO;
	}
	

}