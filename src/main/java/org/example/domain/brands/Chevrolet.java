package org.example.domain.brands;
import org.example.domain.CarPolimorfico;
import org.example.domain.Driver;

public class Chevrolet extends CarPolimorfico {
    public Chevrolet(Long id, int carNumber, Driver driver) {
        super(id, carNumber, driver);
    }

    @Override
    public int calcularVelocidadMaxima() {
        return 275 - (this.kilosDeLastre * 1);
    }
}
