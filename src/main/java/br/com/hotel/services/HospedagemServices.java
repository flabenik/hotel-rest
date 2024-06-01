package br.com.hotel.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotel.data.HospedagemVO;
import br.com.hotel.mapper.ModMapper;
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
	

}