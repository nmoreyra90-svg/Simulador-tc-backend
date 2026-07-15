package org.example.domain;

public class Driver {

    private Long id;
    private String name;
    private int mmr; // Skill
    private int championshipPoints;
    private boolean hasVictory;
    public Driver(Long id, String name, int mmr) {
        this.id = id;
        this.name = name;
        this.mmr = mmr;
        this.championshipPoints = 0;
        this.hasVictory = false;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getMmr() { return mmr; }
    public int getChampionshipPoints() { return championshipPoints; }

    public void addPoints(int points) {
        this.championshipPoints += points;
    }
    public boolean hasVictory() {
        return hasVictory;
    }

    public void registerVictory() {
        this.hasVictory = true;
    }
}