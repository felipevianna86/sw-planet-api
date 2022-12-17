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

import static com.example.swplanetapi.common.PlanetConstants.PLANET;
import static com.example.swplanetapi.common.PlanetConstants.INVALID_PLANET;
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

}
