package org.example.dto;

import org.example.domain.Driver;

public record DriverDTO(
        Long id,
        String name,
        int mmr,
        int championshipPoints,
        boolean hasVictory
) {

    public static DriverDTO fromEntity(Driver driver) {
        return new DriverDTO(
                driver.getId(),
                driver.getName(),
                driver.getMmr(),
                driver.getChampionshipPoints(),
                driver.hasVictory()
        );
    }
}