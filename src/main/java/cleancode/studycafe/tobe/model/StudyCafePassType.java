package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.exception.AppException;

public enum StudyCafePassType {

    HOURLY("시간 이용권(자유석)", DurationType.HOURLY),
    WEEKLY("주 이용권(자유석)", DurationType.WEEKLY),
    FIXED("1인 고정석", DurationType.WEEKLY),;

    private final String description;
    private final DurationType durationType;

    StudyCafePassType(String description, DurationType durationType) {
        this.description = description;
        this.durationType = durationType;
    }

    public String getDescription() {
        return description;
    }

    public DurationType getDurationType() {
        return durationType;
    }

    public static StudyCafePassType indexOf(int index) {
        return StudyCafePassType.values()[index];
    }
}
