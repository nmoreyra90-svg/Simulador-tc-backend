package org.example.domain;

import java.util.ArrayList;
import java.util.List;

public class Championship {

    // 1. ATRIBUTOS (El estado del campeonato)
    private List<Driver> etapaRegular;
    private List<Driver> copaDeOro;

    // 2. CONSTRUCTOR (Cómo nace un torneo vacío)
    public Championship() {
        // Inicializamos las listas vacías listas para recibir pilotos
        this.etapaRegular = new ArrayList<>();
        this.copaDeOro = new ArrayList<>();
    }

    // 3. GETTERS (Para consultar quiénes están en cada etapa)
    public List<Driver> getEtapaRegular() {
        return etapaRegular;
    }

    // 4. MÉTODOS DE ACCIÓN (Comportamiento)
    public void inscribirPiloto(Driver piloto) {
        this.etapaRegular.add(piloto);
        System.out.println("Piloto inscrito con éxito en la Etapa Regular: " + piloto.getName());
    }

    public List<Driver> getCopaDeOro() {
        return copaDeOro;
    }
    // --- LÓGICA DE NEGOCIO ACTC ---
    public void clasificarCopaDeOro() {
        // 1. Ordenamos la lista de la Etapa Regular de mayor a menor según los puntos
        this.etapaRegular.sort((piloto1, piloto2) ->
                Integer.compare(piloto2.getChampionshipPoints(), piloto1.getChampionshipPoints())
        );

        // 2. Definimos el límite (12 pilotos, o menos si por algún motivo no llegamos a 12 inscritos)
        int limite = Math.min(12, this.etapaRegular.size());

        // 3. Vaciamos la Copa de Oro por si se corre dos veces, y pasamos a los clasificados
        this.copaDeOro.clear();
        for (int i = 0; i < limite; i++) {
            this.copaDeOro.add(this.etapaRegular.get(i));
        }

        System.out.println("Clasificación a la Copa de Oro completada. Ingresaron " + limite + " pilotos.");
    }
}
