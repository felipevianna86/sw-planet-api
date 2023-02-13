package com.example.swplanetapi.repository;

import com.example.swplanetapi.domain.Planet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static com.example.swplanetapi.common.PlanetConstants.PLANET;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PlanetRepositoryTest {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private TestEntityManager testEntityManager;
    @Test
    public void criarPlanetaComDadosValidos(){
        Planet planetaCriado = planetRepository.save(PLANET);

        Planet planetaSobTeste = testEntityManager.find(Planet.class, planetaCriado.getId());

        assertThat(planetaSobTeste).isNotNull();
        assertThat(planetaSobTeste.getClimate()).isEqualTo(PLANET.getClimate());
        assertThat(planetaSobTeste.getName()).isEqualTo(PLANET.getName());
        assertThat(planetaSobTeste.getTerrain()).isEqualTo(PLANET.getTerrain());

    }
}
