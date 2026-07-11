package org.example.domain.brands;
import org.example.domain.CarPolimorfico;
import org.example.domain.Driver;

public class Ford extends CarPolimorfico {
    public Ford(Long id, int carNumber, Driver driver) {
        super(id, carNumber, driver);
    }

    @Override
    public int calcularVelocidadMaxima() {
        return 280 - (this.kilosDeLastre * 2);
    }
}
