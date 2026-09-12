package org.example.service;

import org.example.domain.Championship;
import org.example.exception.CampeonatoInvalidoException;
import org.example.repository.ChampionshipRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChampionshipServiceTest {

    @Mock
    private ChampionshipRepository championshipRepository;

    @InjectMocks
    private ChampionshipService championshipService;

    @Test
    void ejecutarClasificacionCopaDeOro_SinPilotosSuficientes_LanzaExcepcion() {
        // 1. PREPARACIÓN (Arrange)
        Long campeonatoId = 1L;
        Championship torneoVacio = new Championship(); // Campeonato con 0 pilotos

        // Simulamos la base de datos con Mockito
        when(championshipRepository.findById(campeonatoId)).thenReturn(Optional.of(torneoVacio));

        // 2. EJECUCIÓN (Act) y 3. VALIDACIÓN (Assert)
        CampeonatoInvalidoException excepcion = assertThrows(
                CampeonatoInvalidoException.class,
                () -> championshipService.ejecutarClasificacionCopaDeOro(campeonatoId)
        );

        // Verificamos que el mensaje sea exactamente el que programaste
        assertEquals("Imposible iniciar la Copa de Oro. El reglamento exige un mínimo de 12 pilotos en la Etapa Regular, pero hay registrados 0 pilotos.", excepcion.getMessage());
    }

    @Test
    void ejecutarClasificacionUltimoMinuto_SinCopaDeOro_LanzaExcepcion() {
        // 1. PREPARACIÓN (Arrange)
        Long campeonatoId = 1L;
        // Creamos un campeonato nuevo (por defecto tiene 0 pilotos en la Copa de Oro)
        Championship torneoSinCopa = new Championship();

        when(championshipRepository.findById(campeonatoId)).thenReturn(Optional.of(torneoSinCopa));

        // 2. EJECUCIÓN (Act) y 3. VALIDACIÓN (Assert)
        IllegalStateException excepcion = assertThrows(
                IllegalStateException.class,
                () -> championshipService.ejecutarClasificacionUltimoMinuto(campeonatoId)
        );

        // Verificamos que el mensaje exacto de estado inconsistente salte correctamente
        assertEquals("Inconsistencia en el campeonato: No se pueden clasificar los 3 de último minuto porque la Copa de Oro no está formada.", excepcion.getMessage());
    }

}