package com.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.reactive.model.Vehicle;

import reactor.core.publisher.Mono;

@Repository
public interface VehicleRepository extends ReactiveMongoRepository<Vehicle, String> {

    Mono<Vehicle> findByCode(String code);

    Mono<Void> deleteByCode(String code);
}
