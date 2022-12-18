package com.example.swplanetapi.service;

import com.example.swplanetapi.domain.Planet;
import com.example.swplanetapi.repository.PlanetRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.swplanetapi.common.PlanetConstants.INVALID_PLANET;
import static com.example.swplanetapi.common.PlanetConstants.PLANET;
import static org.assertj.core.api.Assertions.assertThat;

;

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

}
