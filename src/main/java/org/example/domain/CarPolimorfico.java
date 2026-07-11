package org.example.domain;

public abstract class CarPolimorfico {
    private Long id;
    private int carNumber;
    private Driver driver;
    protected int kilosDeLastre;

    public CarPolimorfico(Long id, int carNumber, Driver driver) {
        this.id = id;
        this.carNumber = carNumber;
        this.driver = driver;
        this.kilosDeLastre = 0;
    }

    // Método que cada marca debe implementar a su manera
    public abstract int calcularVelocidadMaxima();

    public void agregarLastre(int kilos) {
        this.kilosDeLastre += kilos;
    }

    // Getters
    public Long getId() { return id; }
    public int getCarNumber() { return carNumber; }
    public Driver getDriver() { return driver; }
    public int getKilosDeLastre() { return kilosDeLastre; }
}
