package model;

public class Astronaut {
    private int id;
    private String name;
    private String spacecraft;
    private AstronautStatus status;
    private int experienceLevel;

    public Astronaut(int id,String name,String spacecraft,AstronautStatus status,int experienceLevel) {
        this.id = id;
        this.name = name;
        this.spacecraft = spacecraft;
        this.status = status;
        this.experienceLevel = experienceLevel;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSpacecraft() {
        return spacecraft;
    }
    public void setSpacecraft(String spacecraft) {
        this.spacecraft = spacecraft;
    }
    public AstronautStatus getStatus() {
        return status;
    }
    public void setStatus(AstronautStatus status) {
        this.status = status;
    }
    public int getExperienceLevel() {
        return experienceLevel;
    }
    public void setExperienceLevel(int experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    @Override
    public String toString() {
        return String.format("[#%d] %s | %s | %s | exp=%d",
                id, name, spacecraft, status, experienceLevel);
    }
}
