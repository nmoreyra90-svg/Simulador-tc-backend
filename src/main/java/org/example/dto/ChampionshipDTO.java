package org.example.dto;

import org.example.domain.Championship;
import java.util.List;

public record ChampionshipDTO(
        Long id,
        List<DriverDTO> etapaRegular,
        List<DriverDTO> copaDeOro,
        List<DriverDTO> eliminadosEtapaRegular // 1. Agregamos el atributo requerido
) {
    public static ChampionshipDTO fromEntity(Championship championship) {

        // 2. Lógica de dominio aislada en el DTO (Propiedad Calculada)
        List<DriverDTO> pilotosEliminados = championship.getEtapaRegular().stream()
                .filter(driver -> !championship.getCopaDeOro().contains(driver))
                .map(DriverDTO::fromEntity)
                .toList(); // Java 16+: más limpio y garantiza inmutabilidad

        // 3. Retornamos la instancia inmutable
        return new ChampionshipDTO(
                championship.getId(),
                championship.getEtapaRegular().stream()
                        .map(DriverDTO::fromEntity)
                        .toList(),
                championship.getCopaDeOro().stream()
                        .map(DriverDTO::fromEntity)
                        .toList(),
                pilotosEliminados // Inyectamos el cálculo en memoria
        );
    }
}