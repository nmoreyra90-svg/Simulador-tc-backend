package org.example.domain.brands;

import org.example.domain.CarPolimorfico;
import org.example.domain.Driver;

public class Dodge extends CarPolimorfico {
    public Dodge(Long id, int carNumber, Driver driver) {
        super(id, carNumber, driver);
    }

    @Override
    public int calcularVelocidadMaxima() {
        return 285 - (this.kilosDeLastre * 3);
    }
}
