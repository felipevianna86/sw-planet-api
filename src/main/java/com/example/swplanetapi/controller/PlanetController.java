package com.example.swplanetapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.swplanetapi.domain.Planet;
import com.example.swplanetapi.service.PlanetService;

@RestController
@RequestMapping("/planet")
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    @PostMapping
    public ResponseEntity<Planet> create(@RequestBody Planet planet){

        return ResponseEntity.status(HttpStatus.CREATED).body(planetService.create(planet));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planet> findById(@PathVariable Long id){
        return planetService.findById(id).map( planet -> ResponseEntity.ok(planet))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Planet> findByName(@PathVariable String name){
        return planetService.findByName(name).map( planet -> ResponseEntity.ok(planet))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
