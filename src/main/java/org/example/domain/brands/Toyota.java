package org.example.domain.brands;

import org.example.domain.CarPolimorfico;
import org.example.domain.Driver;

public class Toyota extends CarPolimorfico {
    public Toyota(Long id, int carNumber, Driver driver) {
        super(id, carNumber, driver);
    }

    @Override
    public int calcularVelocidadMaxima() {
        return 282 - (this.kilosDeLastre / 2); // Excelencia aerodinámica, casi no siente el lastre
    }
}
