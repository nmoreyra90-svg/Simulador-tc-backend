package org.example.domain.brands;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.example.domain.CarPolimorfico;
import org.example.domain.Driver;

@Entity
@DiscriminatorValue("Dodge")
public class Dodge extends CarPolimorfico {

    protected Dodge() {}

    public Dodge(Long id, int carNumber, Driver driver) {
        super(id, carNumber, driver);
    }

    @Override
    public int calcularVelocidadMaxima() {
        return 285 - (this.kilosDeLastre * 3);
    }
}
