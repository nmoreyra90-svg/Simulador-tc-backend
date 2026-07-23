package org.example.service;

import org.example.domain.Championship;
import org.example.domain.Driver;
import org.example.repository.ChampionshipRepository;
import org.example.repository.DriverRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChampionshipService {

    private final ChampionshipRepository championshipRepository;
    private final DriverRepository driverRepository;

    // Práctica Senior: Inyección de dependencias por constructor
    public ChampionshipService(ChampionshipRepository championshipRepository, DriverRepository driverRepository) {
        this.championshipRepository = championshipRepository;
        this.driverRepository = driverRepository;
    }

    @Transactional
    public Championship inicializarNuevoCampeonato() {
        Championship torneo = new Championship();
        return championshipRepository.save(torneo);
    }

    @Transactional
    public void generarEInscribirPilotosDePrueba(Long campeonatoId) {
        Championship torneo = championshipRepository.findById(campeonatoId)
                .orElseThrow(() -> new RuntimeException("Error: Campeonato no encontrado con ID " + campeonatoId));

        // Lista inyectada con la grilla real del Turismo Carretera
        String[] grillaTC = {
                "Mariano Werner", "Julián Santero", "José Manuel Urcera", "Jonatan Castellano",
                "Santiago Mangoni", "Mauricio Lambiris", "Matias Rossi", "Germán Todino",
                "Gastón Mazzacane", "Juan Martín Trucco", "Valentín Aguirre", "Christian Ledesma",
                "Nicolás Trosset", "Agustin Canapino", "Facundo Ardusso"
        };

        for (String nombrePiloto : grillaTC) {
            Driver piloto = new Driver(null, nombrePiloto, 80);
            int puntosAleatorios = (int) (Math.random() * 300);
            piloto.addPoints(puntosAleatorios);

            Driver pilotoGuardado = driverRepository.save(piloto);
            torneo.inscribirPiloto(pilotoGuardado);
        }

        championshipRepository.save(torneo);
    }

    @Transactional
    public void ejecutarClasificacionCopaDeOro(Long campeonatoId) {
        Championship torneo = championshipRepository.findById(campeonatoId)
                .orElseThrow(() -> new RuntimeException("Error: Campeonato no encontrado con ID " + campeonatoId));

        torneo.clasificarCopaDeOro();
        championshipRepository.save(torneo);
    }

    @Transactional
    public void ejecutarClasificacionUltimoMinuto(Long campeonatoId) {
        Championship torneo = championshipRepository.findById(campeonatoId)
                .orElseThrow(() -> new RuntimeException("Error: Campeonato no encontrado con ID " + campeonatoId));

        torneo.clasificarTresDeUltimoMinuto();
        championshipRepository.save(torneo);
    }

    @Transactional(readOnly = true)
    public List<Driver> obtenerClasificadosCopaDeOro(Long campeonatoId) {
        Championship torneo = championshipRepository.findById(campeonatoId)
                .orElseThrow(() -> new RuntimeException("Error: Campeonato no encontrado"));

        torneo.getCopaDeOro().size();
        return torneo.getCopaDeOro();
    }
}
