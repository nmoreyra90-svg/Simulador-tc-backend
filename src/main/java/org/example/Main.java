package org.example;

import org.example.domain.Championship;
import org.example.domain.Track;
import org.example.domain.Driver;

public class Main {
    public static void main(String[] args) {
        Track rafaela = new Track(1L, "Autódromo Ciudad de Rafaela", "Rafaela", 4740);
        System.out.println("Pista cargada: " + rafaela.getName() + " - Longitud: " + rafaela.getLengthInMeters() + " metros.");
        Driver piloto = new Driver(1L, "Mariano Werner", 37);
        // Creamos el torneo vacío
        Championship torneo = new Championship();

        // 1. GENERADOR DE PILOTOS DE PRUEBA
        for (int i = 1; i <= 15; i++) {
            // Creamos un piloto genérico. Su ID y su nombre cambian en cada vuelta.
            Driver pilotoPrueba = new Driver((long) i, "Piloto Genérico " + i, 80);

            // Le damos puntos al azar (entre 0 y 300) para poder probar el filtro
            int puntosAleatorios = (int) (Math.random() * 300);
            pilotoPrueba.addPoints(puntosAleatorios);

            // Lo inscribimos al torneo
            torneo.inscribirPiloto(pilotoPrueba);
        }

        System.out.println("--- FIN DE INSCRIPCIONES ---");

        // 2. LA PRUEBA DE FUEGO (El Paso 3)
        // Ejecutamos el motor de clasificación
        torneo.clasificarCopaDeOro();

        // Restauramos el título que se había borrado
        System.out.println("--- LOS 12 CLASIFICADOS ---");

        // 3. MOSTRAMOS EL RESULTADO (Los 12 originales)
        // Le pedimos al torneo la lista de la Copa de Oro y la imprimimos
        for (Driver clasificado : torneo.getCopaDeOro()) {
            System.out.println(clasificado.getName() + " - Puntos: " + clasificado.getChampionshipPoints());
        }

        // 4. LOS 3 DE ÚLTIMO MINUTO
        // Ya clasificamos y mostramos a los 12, ahora ejecutamos el nuevo método
        torneo.clasificarTresDeUltimoMinuto();
    }
}
