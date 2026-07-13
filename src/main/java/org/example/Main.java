package org.example;

import org.example.domain.Track;
import org.example.domain.Driver;

public class Main {
    public static void main(String[] args) {
        Track rafaela = new Track(1L, "Autódromo Ciudad de Rafaela", "Rafaela", 4740);
        System.out.println("Pista cargada: " + rafaela.getName() + " - Longitud: " + rafaela.getLengthInMeters() + " metros.");
        Driver piloto = new Driver(1L, "Mariano Werner", 37);
        System.out.println("Piloto inscrito: " + piloto.getName());
    }
}
