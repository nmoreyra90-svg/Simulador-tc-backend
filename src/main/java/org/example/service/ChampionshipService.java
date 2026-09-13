package org.example.service;

import org.example.domain.Championship;
import org.example.domain.Driver;
import org.example.repository.ChampionshipRepository;
import org.example.repository.DriverRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.exception.RecursoNoEncontradoException;

import java.util.Collections;
import java.util.List;

@Service
public class ChampionshipService {

    private final ChampionshipRepository championshipRepository;
    private final DriverRepository driverRepository;


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
                .orElseThrow(() -> new RecursoNoEncontradoException("Error: Campeonato no encontrado con ID " + campeonatoId));


        String[] grillaTC = {
                "Mariano Werner", "Julián Santero", "José Manuel Urcera", "Jonatan Castellano",
                "Santiago Mangoni", "Mauricio Lambiris", "Matias Rossi", "Germán Todino",
                "Gastón Mazzacane", "Juan Martín Trucco", "Valentín Aguirre", "Christian Ledesma",
                "Nicolás Trosset", "Agustin Canapino", "Facundo Ardusso", "Diego Ciantini", "Marcos Landa",
                "Facundo Chapur", "Otto Fritzler", "Juan Cruz Benvenuti"
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
                .orElseThrow(() -> new RecursoNoEncontradoException("Error: Campeonato no encontrado con ID " + campeonatoId));

        torneo.clasificarCopaDeOro();
        championshipRepository.save(torneo);
    }

    @Transactional
    public void ejecutarClasificacionUltimoMinuto(Long campeonatoId) {
        Championship torneo = championshipRepository.findById(campeonatoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Error: Campeonato no encontrado con ID " + campeonatoId));

        torneo.clasificarTresDeUltimoMinuto();
        championshipRepository.save(torneo);
    }

    @Transactional(readOnly = true)
    public List<Driver> obtenerClasificadosCopaDeOro(Long campeonatoId) {
        Championship torneo = championshipRepository.findById(campeonatoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Error: Campeonato no encontrado"));

        return torneo.getCopaDeOro();
    }

    @Transactional
    public void simularFechaCopaDeOro(Long campeonatoId) {


        Championship torneo = championshipRepository.findById(campeonatoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Error: Campeonato no encontrado con ID " + campeonatoId));


        List<Driver> pilotosCopaDeOro = torneo.getCopaDeOro();


        if (pilotosCopaDeOro.size() != 15) {
            throw new IllegalStateException("Fallo de dominio: La simulación requiere exactamente 15 pilotos clasificados.");
        }


        Collections.shuffle(pilotosCopaDeOro);


        for (int i = 0; i < pilotosCopaDeOro.size(); i++) {
            Driver piloto = pilotosCopaDeOro.get(i);

            if (i == 0) {

                piloto.addPoints(45);
                piloto.registerVictory();
            } else {

                int puntosPosicion = 35 - (i * 2);
                piloto.addPoints(Math.max(0, puntosPosicion));
            }
        }

        championshipRepository.save(torneo);
    }
}