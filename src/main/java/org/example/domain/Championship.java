package org.example.domain;

import org.example.exception.CampeonatoInvalidoException;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Optional;

@Entity
public class Championship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "championship_etapa_regular",
            joinColumns = @JoinColumn(name = "championship_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id")
    )
    private List<Driver> etapaRegular;


    @ManyToMany
    @JoinTable(
            name = "championship_copa_oro",
            joinColumns = @JoinColumn(name = "championship_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id")
    )
    private List<Driver> copaDeOro;

    public Championship() {
        this.etapaRegular = new ArrayList<>();
        this.copaDeOro = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public List<Driver> getEtapaRegular() {
        return etapaRegular;
    }

    public void inscribirPiloto(Driver piloto) {
        this.etapaRegular.add(piloto);
    }

    public List<Driver> getCopaDeOro() {
        return copaDeOro;
    }

    public void clasificarCopaDeOro() {
        // 1. EL ESCUDO: Si no hay 12 pilotos, cortamos la ejecución y lanzamos el error
        if (this.etapaRegular.size() < 12) {
            throw new CampeonatoInvalidoException("Imposible iniciar la Copa de Oro. El reglamento exige un mínimo de 12 pilotos en la Etapa Regular, pero hay registrados " + this.etapaRegular.size() + " pilotos.");
        }

        // 2. Lógica normal
        this.etapaRegular.sort((piloto1, piloto2) ->
                Integer.compare(piloto2.getChampionshipPoints(), piloto1.getChampionshipPoints())
        );

        this.copaDeOro.clear();
        for (int i = 0; i < 12; i++) {
            this.copaDeOro.add(this.etapaRegular.get(i));
        }
    }

    public void clasificarTresDeUltimoMinuto() {
        // 1. EL ESCUDO: Validamos que la Copa de Oro ya exista antes de agregar los de último minuto
        if (this.copaDeOro.size() < 12) {
            throw new IllegalStateException("Inconsistencia en el campeonato: No se pueden clasificar los 3 de último minuto porque la Copa de Oro no está formada.");
        }

        java.util.List<Driver> tresNuevos = this.etapaRegular.stream()
                .filter(driver -> !this.copaDeOro.contains(driver))
                .sorted((d1, d2) -> Integer.compare(d2.getChampionshipPoints(), d1.getChampionshipPoints()))
                .limit(3)
                .toList();

        this.copaDeOro.addAll(tresNuevos);
    }

    public Optional<Driver> obtenerCampeon() {
        return this.copaDeOro.stream()
                .filter(Driver::hasVictory)
                .max(Comparator.comparingInt(Driver::getChampionshipPoints));
    }
}