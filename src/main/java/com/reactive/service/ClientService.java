package com.reactive.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactive.converter.ClienteConverter;
import com.reactive.dto.ClientDTO;
import com.reactive.dto.ResponseDTO;
import com.reactive.model.Client;
import com.reactive.repository.ClientRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public Mono<ResponseDTO> create(ClientDTO clientDTO) {

		Client client = ClienteConverter.toClient(clientDTO);
		Mono<Client> mono = this.clientRepository.save(client);
		return mono
				.map((clientDocument) -> new ResponseDTO("Cliente cadastrado com sucesso!",
						ClienteConverter.toClientDTO(clientDocument), LocalDateTime.now()))
				.onErrorReturn(new ResponseDTO("Erro ao cadastrar cliente", new ClientDTO(), LocalDateTime.now()));

	}

	public Flux<ResponseDTO<ClientDTO>> getAll() {
		Flux<Client> clientFlux = this.clientRepository.findAll();
		return clientFlux.map(client -> new ResponseDTO("Listagem de clientes retornada com sucesso!",
				ClienteConverter.toClientDTO(client), LocalDateTime.now()));
	}

	public Mono<ResponseDTO<ClientDTO>> findByEmail(String email) {
		Mono<Client> clientMono = this.clientRepository.findByEmail(email);
		return clientMono.map(client -> new ResponseDTO("Busca por email retornada com sucesso!",
				ClienteConverter.toClientDTO(client), LocalDateTime.now()));

	}

	public Mono<ResponseDTO> update(ClientDTO clientDTO) {

		Mono<Client> clientMono = this.clientRepository.findByEmail(clientDTO.getEmail());

		return clientMono.flatMap((clint) -> {
			clint.setName(clientDTO.getName());
			clint.setAge(clientDTO.getAge());
			clint.setEmail(clientDTO.getEmail());
	
			return this.clientRepository.save(clint);
		}).map(client -> new ResponseDTO<>("Cliente alterado com sucesso!", ClienteConverter.toClientDTO(client),
				LocalDateTime.now()));
	}

	public Mono<ResponseDTO> delete(String code) {
		return this.clientRepository.deleteById(code)
				.map((client) -> new ResponseDTO<>("Cliente removido com sucesso!", null, LocalDateTime.now()));
	}

}
