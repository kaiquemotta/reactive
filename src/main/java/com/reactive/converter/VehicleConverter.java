package com.reactive.converter;

import com.reactive.dto.VehicleDTO;
import com.reactive.model.Vehicle;

public class VehicleConverter {

	public static VehicleDTO toVehicleDTO(Vehicle vehicle) {
		return new VehicleDTO(vehicle.getCode(), vehicle.getModel(), vehicle.getManufacturer(), vehicle.getColor(),
				vehicle.getValue());
	}

	public static Vehicle toVehicle(VehicleDTO vehicleDTO) {
		return new Vehicle(vehicleDTO.getCode(), vehicleDTO.getModel(), vehicleDTO.getManufacturer(),
				vehicleDTO.getColor(), vehicleDTO.getValue());
	}

}
