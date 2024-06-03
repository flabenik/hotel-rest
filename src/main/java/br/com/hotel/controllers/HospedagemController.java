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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/hospedagem/v1")
@Tag(name = "Hospedagem", description = "Endpoints para manter Hospedagem")
public class HospedagemController {
	
	@Autowired
	private HospedagemServices service;

	
	@GetMapping(value = "/buscaTodos", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Busca Todas as Hospedagens", description = "Busca Todas as Hospedagens",
	responses = {
		@ApiResponse(description = "Success", responseCode = "200",
			content = {
				@Content(
					mediaType = "application/json",
					array = @ArraySchema(schema = @Schema(implementation = HospedagemVO.class))
				)
			}),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
				}
			)
	public List<HospedagemVO> findAll(){		
		return service.findAll();
	}
	
	@GetMapping(value = "/buscaHospedagemId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Busca Hospedagem Por ID", description = "Busca Hospedagem Por ID",
	responses = {
		@ApiResponse(description = "Success", responseCode = "200",
			content = {
				@Content(
					mediaType = "application/json",
					array = @ArraySchema(schema = @Schema(implementation = HospedagemVO.class))
				)
			}),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
				}
			)
	public HospedagemVO findById(
			@PathVariable(value="id") Long id ) throws Exception {		
		return service.findById(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Cadastra Hospedagens", description = "Cadastra Hospedagens",
	responses = {
		@ApiResponse(description = "Success", responseCode = "200",
			content = {
				@Content(
					mediaType = "application/json",
					array = @ArraySchema(schema = @Schema(implementation = HospedagemVO.class))
				)
			}),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
				}
			)
	public HospedagemVO cadastraHospedagem(
			@RequestBody HospedagemVO hospedagem ) throws Exception {		
		return service.cadastraHospedagem(hospedagem);
	}
	
}