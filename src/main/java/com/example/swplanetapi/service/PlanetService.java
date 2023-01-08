package com.example.swplanetapi.service;

import com.example.swplanetapi.builder.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.swplanetapi.domain.Planet;
import com.example.swplanetapi.repository.PlanetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetService {

    private PlanetRepository planetRepository;

    @Autowired
    public PlanetService(PlanetRepository planetRepository){
        this.planetRepository = planetRepository;
    }

    public Planet create(Planet planet){
        return planetRepository.save(planet);
    }

    public Optional<Planet> findById(Long id){
        return planetRepository.findById(id);
    }

    public Optional<Planet> findByName(String name){
        return planetRepository.findByName(name);
    }

    public List<Planet> list(String terrain, String climate){
        Example<Planet> query = QueryBuilder.makeQuery(new Planet(climate, terrain));

        return planetRepository.findAll(query);
    }

    public void remove(Long id){
        planetRepository.deleteById(id);
    }
}
