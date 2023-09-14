package com.reactive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "vehicles")
public class Vehicle {

	@Id
	private String id = new ObjectId().toString();
	@Indexed(unique = true, background = true)
	private String code;
	private String model;
	private String manufacturer;
	private String color;
	private BigDecimal value;

	public Vehicle(String code, String model, String manufacturer, String color, BigDecimal value) {
		super();
		this.code = code;
		this.model = model;
		this.manufacturer = manufacturer;
		this.color = color;
		this.value = value;
	}

}
