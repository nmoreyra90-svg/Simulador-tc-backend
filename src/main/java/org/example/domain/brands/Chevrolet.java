package org.example.domain.brands;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.example.domain.CarPolimorfico;
import org.example.domain.Driver;

@Entity
@DiscriminatorValue("CHEVROLET")
public class Chevrolet extends CarPolimorfico {

    protected Chevrolet() {}

    public Chevrolet(Long id, int carNumber, Driver driver) { super(id, carNumber, driver); }

    @Override
    public int calcularVelocidadMaxima() { return 275 - (this.kilosDeLastre * 1); }
}