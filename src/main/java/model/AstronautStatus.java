package model;

public enum AstronautStatus {
    ACTIVE,INACTIVE,LOST;

    public static AstronautStatus fromJson(String jsonStatus) {
        if ("ACTIVE".equalsIgnoreCase(jsonStatus)) return ACTIVE;
        if ("INACTIVE".equalsIgnoreCase(jsonStatus)) return INACTIVE;
        return ACTIVE;
    }
}
