package org.example.domain.brands;

import org.example.domain.CarPolimorfico;
import org.example.domain.Driver;

public class Torino extends CarPolimorfico {
    public Torino(Long id, int carNumber, Driver driver) {
        super(id, carNumber, driver);
    }

    @Override
    public int calcularVelocidadMaxima() {
        return 278 - (this.kilosDeLastre * 1);
    }
}
