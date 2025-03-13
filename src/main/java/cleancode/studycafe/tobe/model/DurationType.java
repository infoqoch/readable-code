package cleancode.studycafe.tobe.model;

public enum DurationType {
    HOURLY("시간권"), WEEKLY("주권");

    private final String description;

    DurationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
