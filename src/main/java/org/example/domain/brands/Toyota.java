package org.example.domain.brands;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.example.domain.Car;
import org.example.domain.Driver;

@Entity
@DiscriminatorValue("TOYOTA")
public class Toyota extends Car {

    protected Toyota() {
    }

    public Toyota(Long id, int carNumber, Driver driver) {
        super(id, carNumber, driver);
    }

    @Override
    public int calcularVelocidadMaxima() {
        return 282 - (this.kilosDeLastre / 2);
    }
}