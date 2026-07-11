package org.example.domain;

public class Car {
    private Long id;
    private String brand;
    private int carNumber;
    private Driver driver;

    public Car(Long id, String brand, int carNumber, Driver driver) {
        this.id = id;
        this.brand = brand;
        this.carNumber = carNumber;
        this.driver = driver;
    }

    public Long getId() { return id; }
    public String getBrand() { return brand; }
    public int getCarNumber() { return carNumber; }
    public Driver getDriver() { return driver; }
}
