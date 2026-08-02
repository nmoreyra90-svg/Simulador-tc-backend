package org.example.controller;

import org.example.domain.Championship;
import org.example.dto.ChampionshipDTO;
import org.example.service.ChampionshipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/championships")
public class ChampionshipController {

    private final ChampionshipService championshipService;

    // Inyección de dependencias por constructor para mantener la consistencia
    public ChampionshipController(ChampionshipService championshipService) {
        this.championshipService = championshipService;
    }

    // Endpoint 1: Inicializar un campeonato vacío
    @PostMapping
    public ResponseEntity<ChampionshipDTO> createChampionship() {
        // 1. Delegamos la mutación de estado al servicio
        Championship nuevoTorneo = championshipService.inicializarNuevoCampeonato();

        // 2. Transformamos la entidad de dominio al DTO de salida
        ChampionshipDTO responseDTO = ChampionshipDTO.fromEntity(nuevoTorneo);

        // 3. Retornamos el objeto encapsulado con el estado HTTP correcto
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}