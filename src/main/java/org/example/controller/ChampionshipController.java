package org.example.controller;

import org.example.domain.Championship;
import org.example.dto.ChampionshipDTO;
import org.example.repository.ChampionshipRepository; // Importación necesaria
import org.example.service.ChampionshipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable; // Importación necesaria
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/championships")
public class ChampionshipController {

    private final ChampionshipService championshipService;
    private final ChampionshipRepository championshipRepository; // 1. Agregamos el repositorio

    // Inyección de dependencias por constructor para mantener la consistencia
    public ChampionshipController(ChampionshipService championshipService, ChampionshipRepository championshipRepository) {
        this.championshipService = championshipService;
        this.championshipRepository = championshipRepository; // 2. Lo inicializamos
    }

    // Endpoint 1: Inicializar un campeonato vacío (TU CÓDIGO INTACTO)
    @PostMapping
    public ResponseEntity<ChampionshipDTO> createChampionship() {
        Championship nuevoTorneo = championshipService.inicializarNuevoCampeonato();
        ChampionshipDTO responseDTO = ChampionshipDTO.fromEntity(nuevoTorneo);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // Endpoint 2: Inscribir a los pilotos de prueba
    @PostMapping("/{id}/inscribir-pilotos-prueba")
    public ResponseEntity<ChampionshipDTO> inscribirPilotosDePrueba(@PathVariable Long id) {
        // 1. Delegamos la lógica al servicio
        championshipService.generarEInscribirPilotosDePrueba(id);

        // 2. Buscamos el campeonato actualizado en PostgreSQL
        Championship torneoActualizado = championshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campeonato no encontrado"));

        // 3. Reutilizamos tu excelente patrón fromEntity
        ChampionshipDTO responseDTO = ChampionshipDTO.fromEntity(torneoActualizado);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // Endpoint 3: Ejecutar algoritmo de la Copa de Oro
    @PostMapping("/{id}/clasificar-copa-oro")
    public ResponseEntity<ChampionshipDTO> clasificarCopaDeOro(@PathVariable Long id) {
        // 1. Delegamos la lógica estricta del reglamento al servicio
        championshipService.ejecutarClasificacionCopaDeOro(id);

        // 2. Buscamos el campeonato con el arreglo copaDeOro actualizado
        Championship torneoActualizado = championshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campeonato no encontrado"));

        // 3. Mapeamos a DTO
        ChampionshipDTO responseDTO = ChampionshipDTO.fromEntity(torneoActualizado);

        // 4. Retornamos 200 OK (no estamos creando un recurso nuevo, solo lo actualizamos)
        return ResponseEntity.ok(responseDTO);
    }
    // Endpoint 4: Obtener un campeonato por ID (Lectura)
    @GetMapping("/{id}")
    public ResponseEntity<ChampionshipDTO> obtenerCampeonato(@PathVariable Long id) {
        // 1. Buscamos el campeonato en la base de datos usando el repositorio que ya inyectaste
        Championship torneo = championshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campeonato no encontrado"));

        // 2. Mapeamos a DTO (Aquí se ejecutará la matemática de los eliminados)
        ChampionshipDTO responseDTO = ChampionshipDTO.fromEntity(torneo);

        // 3. Retornamos 200 OK con el JSON completo
        return ResponseEntity.ok(responseDTO);
    }

    // Endpoint 5: Ejecutar los 3 de último minuto
    @PostMapping("/{id}/clasificar-ultimo-minuto")
    public ResponseEntity<ChampionshipDTO> clasificarUltimoMinuto(@PathVariable Long id) {

        // 1. Ejecutamos tu método existente en el servicio
        championshipService.ejecutarClasificacionUltimoMinuto(id);

        // 2. Recuperamos el torneo actualizado de PostgreSQL
        Championship torneoActualizado = championshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campeonato no encontrado"));

        // 3. Mapeamos a DTO y respondemos
        return ResponseEntity.ok(ChampionshipDTO.fromEntity(torneoActualizado));
    }

}