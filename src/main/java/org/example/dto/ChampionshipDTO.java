package org.example.dto;

import org.example.domain.Championship;
import java.util.List;
import java.util.stream.Collectors;

public record ChampionshipDTO(
        Long id,
        List<DriverDTO> etapaRegular,
        List<DriverDTO> copaDeOro
) {
    public static ChampionshipDTO fromEntity(Championship championship) {
        return new ChampionshipDTO(
                championship.getId(),
                championship.getEtapaRegular().stream()
                        .map(DriverDTO::fromEntity)
                        .collect(Collectors.toList()),
                championship.getCopaDeOro().stream()
                        .map(DriverDTO::fromEntity)
                        .collect(Collectors.toList())
        );
    }
}