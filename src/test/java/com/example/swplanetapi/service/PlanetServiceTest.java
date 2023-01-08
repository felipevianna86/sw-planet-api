package com.example.swplanetapi.service;

import com.example.swplanetapi.builder.QueryBuilder;
import com.example.swplanetapi.domain.Planet;
import com.example.swplanetapi.repository.PlanetRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.swplanetapi.common.PlanetConstants.INVALID_PLANET;
import static com.example.swplanetapi.common.PlanetConstants.PLANET;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

    @InjectMocks
    private PlanetService planetService;

    @Mock
    private PlanetRepository planetRepository;
    
    @Test
    public void criaPlanetaComDadosValidos(){
        Mockito.when(planetRepository.save(PLANET)).thenReturn(PLANET);

        Planet planet = planetService.create(PLANET);

        assertThat(planet).isEqualTo(PLANET);
    }
    @Test
    public void criaPlanetaComDadosInvalidos(){

        Mockito.when(planetRepository.save(INVALID_PLANET)).thenThrow(RuntimeException.class);

        Assertions.assertThatThrownBy(() -> planetService.create(INVALID_PLANET)).isInstanceOf(RuntimeException.class);

    }

    @Test
    public void buscaPlanetaByIdExistente(){

        Mockito.when(planetRepository.findById(1L)).thenReturn(Optional.of(PLANET));

        Optional<Planet> planet = planetService.findById(1L);

        assertThat(planet).isNotEmpty();
        assertThat(planet.get()).isEqualTo(PLANET);
    }

    @Test
    public void buscaPlanetaByIdInexistente(){

        Mockito.when(planetRepository.findById(5L)).thenReturn(Optional.empty());

        Optional<Planet> planet = planetService.findById(5L);

        assertThat(planet).isEmpty();
    }

    @Test
    public void buscaPlanetaByNomeExistente(){

        Mockito.when(planetRepository.findByName(PLANET.getName())).thenReturn(Optional.of(PLANET));

        Optional<Planet> planet = planetService.findByName(PLANET.getName());

        assertThat(planet).isNotEmpty();
        assertThat(planet.get()).isEqualTo(PLANET);
    }

    @Test
    public void buscaPlanetaByNomeInexistente(){
        final String name = "teste";
        Mockito.when(planetRepository.findByName(name)).thenReturn(Optional.empty());

        Optional<Planet> planet = planetService.findByName(name);

        assertThat(planet).isEmpty();
    }

    @Test
    public void retornaTodosPlanetas(){
        List<Planet> planets = new ArrayList<>(){
            {
                add(PLANET);
            }
        };

        Example<Planet> query = QueryBuilder.makeQuery(new Planet(PLANET.getClimate(), PLANET.getTerrain()));

        Mockito.when(planetRepository.findAll(query)).thenReturn(planets);

        List<Planet> list = planetService.list(PLANET.getTerrain(), PLANET.getClimate());

        assertThat(list).isNotEmpty();
        assertThat(list).hasSize(1);
        assertThat(list.get(0)).isEqualTo(PLANET);
    }

    @Test
    public void retornaNenhumPlanetas(){
        Mockito.when(planetRepository.findAll(Mockito.any())).thenReturn(Collections.emptyList());

        List<Planet> list = planetService.list(PLANET.getTerrain(), PLANET.getClimate());

        assertThat(list).isEmpty();
    }

    @Test
    public void removePlanetaENaoRetornaQualquerException(){
        assertThatCode(() -> planetService.remove(1L)).doesNotThrowAnyException();
    }

    @Test
    public void removePlanetaComIdInexistenteERetornaException(){
        Mockito.doThrow(new RuntimeException()).when(planetRepository).deleteById(99L);

        assertThatThrownBy(() -> planetService.remove(99L)).isInstanceOf(RuntimeException.class);
    }

}
