package org.example.domain;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "brand_type", discriminatorType = DiscriminatorType.STRING)
public abstract class CarPolimorfico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int carNumber;

    // Relación: Muchos autos pueden pertenecer a un mismo piloto (o historial)
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    protected int kilosDeLastre;

    // Constructor vacío exigido por JPA
    protected CarPolimorfico() {}

    public CarPolimorfico(Long id, int carNumber, Driver driver) {
        this.id = id;
        this.carNumber = carNumber;
        this.driver = driver;
        this.kilosDeLastre = 0;
    }

    public abstract int calcularVelocidadMaxima();

    public void agregarLastre(int kilos) {
        this.kilosDeLastre += kilos;
    }

    public Long getId() { return id; }
    public int getCarNumber() { return carNumber; }
    public Driver getDriver() { return driver; }
    public int getKilosDeLastre() { return kilosDeLastre; }
}