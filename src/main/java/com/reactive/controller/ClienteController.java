package com.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.dto.ClientDTO;
import com.reactive.dto.ResponseDTO;
import com.reactive.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/clients")
public class ClienteController {

	@Autowired
	private ClientService clientService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@Operation(description = "Create one Client", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody())
	public Mono<?> create(@RequestBody ClientDTO clientDTO) {
		return this.clientService.create(clientDTO);
	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(description = "Find all Client")
	public Flux<ResponseDTO<ClientDTO>> getAll() {
		return this.clientService.getAll();
	}

	@GetMapping("{code}")
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(description = "Find by code of client")
	public Mono<ResponseDTO<ClientDTO>> findByCode(@PathVariable("code") String code) {
		return this.clientService.findByCode(code);
	}

	@PutMapping
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(description = "Update one Client")
	public Mono<?> update(@RequestBody ClientDTO clientDTO) {
		return this.clientService.update(clientDTO);
	}

	@DeleteMapping("{code}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public Mono<?> delete(@PathVariable("code") String code) {
		return this.clientService.delete(code);
	}

}
