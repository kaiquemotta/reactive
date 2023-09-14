package com.reactive.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactive.converter.VehicleConverter;
import com.reactive.dto.VehicleDTO;
import com.reactive.dto.ResponseDTO;
import com.reactive.model.Vehicle;
import com.reactive.repository.VehicleRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	public Mono<ResponseDTO> create(VehicleDTO vehicleDTO) {

		Vehicle vehicle = VehicleConverter.toVehicle(vehicleDTO);
		Mono<Vehicle> mono = this.vehicleRepository.save(vehicle);
		return mono
				.map((vehicleDodument) -> new ResponseDTO("Veiculo cadastrado com sucesso!",
						VehicleConverter.toVehicleDTO(vehicleDodument), LocalDateTime.now()))
				.onErrorReturn(new ResponseDTO("Erro ao cadastrar veiculo", new VehicleDTO(), LocalDateTime.now()));

	}

	public Flux<ResponseDTO<VehicleDTO>> getAll() {
		Flux<Vehicle> vehicleFlux = this.vehicleRepository.findAll();
		return vehicleFlux.map(vehicle -> new ResponseDTO("Listagem de veiculos retornada com sucesso!",
				VehicleConverter.toVehicleDTO(vehicle), LocalDateTime.now()));
	}

	public Mono<ResponseDTO<VehicleDTO>> findByCode(String code) {
		Mono<Vehicle> vehicleMono = this.vehicleRepository.findByCode(code);
		return vehicleMono.map(vehicle -> new ResponseDTO("Busca por code retornada com sucesso!",
				VehicleConverter.toVehicleDTO(vehicle), LocalDateTime.now()));

	}

	public Mono<ResponseDTO> update(VehicleDTO vehicleDTO) {

		Mono<Vehicle> vehicleMono = this.vehicleRepository.findByCode(vehicleDTO.getCode());

		return vehicleMono.flatMap((existingVehicle) -> {
			existingVehicle.setCode(vehicleDTO.getCode());
			existingVehicle.setColor(vehicleDTO.getColor());
			existingVehicle.setManufacturer(vehicleDTO.getManufacturer());
			existingVehicle.setModel(vehicleDTO.getModel());
			existingVehicle.setValue(vehicleDTO.getValue());
			return this.vehicleRepository.save(existingVehicle);
		}).map(vehicle -> new ResponseDTO<>("Veiculo alterado com sucesso!", VehicleConverter.toVehicleDTO(vehicle),
				LocalDateTime.now()));
	}

	public Mono<ResponseDTO> delete(String code) {
		return this.vehicleRepository.deleteByCode(code)
				.map((vehicle) -> new ResponseDTO<>("Veiculo removido com sucesso!", null, LocalDateTime.now()));
	}

}
