package org.example.domain.brands;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.example.domain.CarPolimorfico;
import org.example.domain.Driver;

@Entity
@DiscriminatorValue("TORINO")
public class Torino extends CarPolimorfico {

    protected Torino() {}

    public Torino(Long id, int carNumber, Driver driver) { super(id, carNumber, driver); }

    @Override
    public int calcularVelocidadMaxima() { return 278 - (this.kilosDeLastre * 1); }
}
