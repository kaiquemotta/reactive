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

import com.reactive.dto.VehicleDTO;
import com.reactive.dto.ResponseDTO;
import com.reactive.service.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/vehicles")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@Operation(description = "Create one Vehicle", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody())
	public Mono<?> create(@RequestBody VehicleDTO vehicleDTO) {
		return this.vehicleService.create(vehicleDTO);
	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(description = "Find all Vehicles")
	public Flux<ResponseDTO<VehicleDTO>> getAll() {
		return this.vehicleService.getAll();
	}

	@GetMapping("{code}")
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(description = "Find by code of vehicle")
	public Mono<ResponseDTO<VehicleDTO>> findByCode(@PathVariable("code") String code) {
		return this.vehicleService.findByCode(code);
	}

	@PutMapping
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(description = "Update one vehicle")
	public Mono<?> update(@RequestBody VehicleDTO vehicleDTO) {
		return this.vehicleService.update(vehicleDTO);
	}

	@DeleteMapping("{code}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public Mono<?> delete(@PathVariable("code") String code) {
		return this.vehicleService.delete(code);
	}

}
