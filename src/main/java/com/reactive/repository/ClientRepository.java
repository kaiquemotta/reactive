package com.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.reactive.model.Client;

import reactor.core.publisher.Mono;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, String> {

    Mono<Client> findByEmail(String email);

    Mono<Void> deleteByEmail(String code);
}
