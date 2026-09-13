package org.example.controller;

import org.example.domain.Championship;
import org.example.dto.ChampionshipDTO;
import org.example.repository.ChampionshipRepository;
import org.example.service.ChampionshipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.example.exception.RecursoNoEncontradoException;

@RestController
@RequestMapping("/api/championships")
public class ChampionshipController {

    private final ChampionshipService championshipService;
    private final ChampionshipRepository championshipRepository;

    public ChampionshipController(ChampionshipService championshipService, ChampionshipRepository championshipRepository) {
        this.championshipService = championshipService;
        this.championshipRepository = championshipRepository;
    }

    @PostMapping
    public ResponseEntity<ChampionshipDTO> createChampionship() {
        Championship nuevoTorneo = championshipService.inicializarNuevoCampeonato();
        ChampionshipDTO responseDTO = ChampionshipDTO.fromEntity(nuevoTorneo);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PostMapping("/{id}/inscribir-pilotos-prueba")
    public ResponseEntity<ChampionshipDTO> inscribirPilotosDePrueba(@PathVariable Long id) {

        championshipService.generarEInscribirPilotosDePrueba(id);


        Championship torneoActualizado = championshipRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Campeonato no encontrado"));


        ChampionshipDTO responseDTO = ChampionshipDTO.fromEntity(torneoActualizado);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }


    @PostMapping("/{id}/clasificar-copa-oro")
    public ResponseEntity<ChampionshipDTO> clasificarCopaDeOro(@PathVariable Long id) {

        championshipService.ejecutarClasificacionCopaDeOro(id);


        Championship torneoActualizado = championshipRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Campeonato no encontrado"));


        ChampionshipDTO responseDTO = ChampionshipDTO.fromEntity(torneoActualizado);


        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChampionshipDTO> obtenerCampeonato(@PathVariable Long id) {

        Championship torneo = championshipRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Campeonato no encontrado"));

        ChampionshipDTO responseDTO = ChampionshipDTO.fromEntity(torneo);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/{id}/clasificar-ultimo-minuto")
    public ResponseEntity<ChampionshipDTO> clasificarUltimoMinuto(@PathVariable Long id) {


        championshipService.ejecutarClasificacionUltimoMinuto(id);

        Championship torneoActualizado = championshipRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Campeonato no encontrado"));

        return ResponseEntity.ok(ChampionshipDTO.fromEntity(torneoActualizado));
    }

    @PostMapping("/{id}/simulate-race-copa")
    public ResponseEntity<Void> simularFechaCopaDeOro(@PathVariable Long id) {
        championshipService.simularFechaCopaDeOro(id);
        return ResponseEntity.ok().build();
    }
}