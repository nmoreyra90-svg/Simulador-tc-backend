package org.example.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Championship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tabla intermedia para la etapa regular
    @ManyToMany
    @JoinTable(
            name = "championship_etapa_regular",
            joinColumns = @JoinColumn(name = "championship_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id")
    )
    private List<Driver> etapaRegular;

    // Tabla intermedia para la Copa de Oro
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

    public Long getId() { return id; }

    public List<Driver> getEtapaRegular() { return etapaRegular; }

    public void inscribirPiloto(Driver piloto) {
        this.etapaRegular.add(piloto);
        System.out.println("Piloto inscrito con éxito en la Etapa Regular: " + piloto.getName());
    }

    public List<Driver> getCopaDeOro() { return copaDeOro; }

    public void clasificarCopaDeOro() {
        this.etapaRegular.sort((piloto1, piloto2) ->
                Integer.compare(piloto2.getChampionshipPoints(), piloto1.getChampionshipPoints())
        );
        int limite = Math.min(12, this.etapaRegular.size());
        this.copaDeOro.clear();
        for (int i = 0; i < limite; i++) {
            this.copaDeOro.add(this.etapaRegular.get(i));
        }
        System.out.println("Clasificación a la Copa de Oro completada. Ingresaron " + limite + " pilotos.");
    }

    public void clasificarTresDeUltimoMinuto() {
        List<Driver> tresDeUltimoMinuto = this.etapaRegular.stream()
                .filter(piloto -> !this.copaDeOro.contains(piloto))
                .limit(3)
                .toList();

        this.copaDeOro.addAll(tresDeUltimoMinuto);

        System.out.println("Clasificación de último minuto completada. Ingresaron " + tresDeUltimoMinuto.size() + " pilotos:");
        tresDeUltimoMinuto.forEach(piloto ->
                System.out.println(" - " + piloto.getName() + " - Puntos: " + piloto.getChampionshipPoints())
        );
    }
}

