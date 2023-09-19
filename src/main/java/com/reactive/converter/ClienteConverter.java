package com.reactive.converter;

import com.reactive.dto.ClientDTO;
import com.reactive.model.Client;

public class ClienteConverter {

	public static ClientDTO toClientDTO(Client client) {
		return new ClientDTO(client.getName(), client.getAge(), client.getEmail());
	}

	public static Client toClient(ClientDTO clientDTO) {
		return new Client(clientDTO.getName(), clientDTO.getAge(), clientDTO.getEmail());
	}

}
