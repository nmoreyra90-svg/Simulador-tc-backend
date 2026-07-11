package org.example.domain;

public class Track {
    private Long id;
    private String name;
    private String city;
    private int lengthInMeters;

    public Track(Long id, String name, String city, int lengthInMeters) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.lengthInMeters = lengthInMeters;
    }

    public String getName() { return name; }
    public int getLengthInMeters() { return lengthInMeters; }
}