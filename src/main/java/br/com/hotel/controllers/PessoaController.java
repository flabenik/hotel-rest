package br.com.hotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.data.PessoaVO;
import br.com.hotel.services.PessoaServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PessoaController {
	
	@Autowired
	private PessoaServices service;

	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Finds all People", description = "Finds all People",
			tags = {"People"},
			responses = {
				@ApiResponse(description = "Success", responseCode = "200",
					content = {
						@Content(
							mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = PessoaVO.class))
						)
					}),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
		)
	public PessoaVO findById(
			@PathVariable(value="id") Long id ) throws Exception {		
		return service.findById(id);
	}
	
	@GetMapping(value = "/findPersonByName/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PessoaVO findPersonByName(@PathVariable(value = "nome") String nome) {
		return service.findPersonByName(nome);
	}
	
	@GetMapping(value = "/buscaPorTelefone/{telefone}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PessoaVO buscaPorTelefone(@PathVariable(value = "telefone") String telefone) {
		return service.buscaPorTelefone(telefone);
	}
	
	@GetMapping(value = "/buscaPorDocumento/{documento}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PessoaVO buscaPorDocumento(@PathVariable(value = "documento") String documento) {
		return service.buscaPorDocumento(documento);
	}	
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PessoaVO> findAll(){		
		return service.findAll();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PessoaVO create(
			@RequestBody PessoaVO person ) throws Exception {		
		return service.create(person);
	}
	
	/*
	 * @PostMapping(value = "/v2", consumes = MediaType.APPLICATION_JSON_VALUE,
	 * produces = MediaType.APPLICATION_JSON_VALUE) public PersonVOV2 createV2(
	 * 
	 * @RequestBody PersonVOV2 person ) throws Exception { return
	 * service.createV2(person); }
	 */
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PessoaVO update(
			@RequestBody PessoaVO person ) throws Exception {		
		return service.update(person);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(
			@PathVariable(value="id") Long id ) throws Exception {		
		 service.delete(id);
		 return ResponseEntity.noContent().build();
	}
	
	
	
	                                               
}
