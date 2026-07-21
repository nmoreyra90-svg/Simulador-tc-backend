package org.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private int lengthInMeters;

    // Constructor vacío exigido por JPA
    protected Track() {}

    public Track(Long id, String name, String city, int lengthInMeters) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.lengthInMeters = lengthInMeters;
    }

    public String getName() { return name; }
    public int getLengthInMeters() { return lengthInMeters; }
}