package org.example.domain.brands;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.example.domain.CarPolimorfico;
import org.example.domain.Driver;

@Entity
@DiscriminatorValue("FORD")
public class Ford extends CarPolimorfico {

    protected Ford() {}

    public Ford(Long id, int carNumber, Driver driver) { super(id, carNumber, driver); }

    @Override
    public int calcularVelocidadMaxima() { return 280 - (this.kilosDeLastre * 2); }
}
