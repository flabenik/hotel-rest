package br.com.hotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.data.HospedagemVO;
import br.com.hotel.services.HospedagemServices;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/hospedagem/v1")
@Tag(name = "People", description = "Endpoints for Managing Hospedagem")
public class HospedagemController {
	
	@Autowired
	private HospedagemServices service;

	
	@GetMapping(value = "/buscaTodos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<HospedagemVO> findAll(){		
		return service.findAll();
	}
	
	public HospedagemVO findById(
			@PathVariable(value="id") Long id ) throws Exception {		
		return service.findById(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HospedagemVO cadastraHospedagem(
			@RequestBody HospedagemVO hospedagem ) throws Exception {		
		return service.cadastraHospedagem(hospedagem);
	}
	
}