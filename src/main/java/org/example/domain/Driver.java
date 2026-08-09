package org.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int mmr; // Skill
    private int championshipPoints;
    private boolean hasVictory = false;


    protected Driver() {
    }

    public Driver(Long id, String name, int mmr) {
        this.id = id;
        this.name = name;
        this.mmr = mmr;
        this.championshipPoints = 0;
        this.hasVictory = false;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMmr() {
        return mmr;
    }

    public int getChampionshipPoints() {
        return championshipPoints;
    }

    public void addPoints(int points) {
        this.championshipPoints += points;
    }

    public boolean hasVictory() {
        return hasVictory;
    }

    public void registerVictory() {
        this.hasVictory = true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;

        return id != null && id.equals(driver.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}