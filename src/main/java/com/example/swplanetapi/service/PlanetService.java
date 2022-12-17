package com.example.swplanetapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.swplanetapi.domain.Planet;
import com.example.swplanetapi.repository.PlanetRepository;

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
}
