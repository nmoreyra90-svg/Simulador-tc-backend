package org.example.dto;

import org.example.domain.Championship;
import org.example.domain.Driver;

import java.util.List;

public record ChampionshipDTO(
        Long id,
        List<DriverDTO> etapaRegular,
        List<DriverDTO> copaDeOro,
        List<DriverDTO> eliminadosEtapaRegular,
        String campeon
) {
    public static ChampionshipDTO fromEntity(Championship championship) {


        List<DriverDTO> pilotosEliminados = championship.getEtapaRegular().stream()
                .filter(driver -> !championship.getCopaDeOro().contains(driver))
                .map(DriverDTO::fromEntity)
                .toList();


        String nombreCampeon = championship.obtenerCampeon()
                .map(Driver::getName)
                .orElse("Campeonato en curso / Sin ganador válido");


        return new ChampionshipDTO(
                championship.getId(),
                championship.getEtapaRegular().stream()
                        .map(DriverDTO::fromEntity)
                        .toList(),
                championship.getCopaDeOro().stream()
                        .map(DriverDTO::fromEntity)
                        .toList(),
                pilotosEliminados,
                nombreCampeon
        );
    }
}