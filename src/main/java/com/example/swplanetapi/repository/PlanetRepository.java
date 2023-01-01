package com.example.swplanetapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.swplanetapi.domain.Planet;

import java.util.Optional;

public interface PlanetRepository extends CrudRepository<Planet, Long>{

    Optional<Planet> findByName(String name);
}
