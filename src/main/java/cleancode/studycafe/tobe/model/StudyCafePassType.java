package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.exception.AppException;

public enum StudyCafePassType {

    HOURLY("시간 이용권(자유석)"),
    WEEKLY("주 이용권(자유석)"),
    FIXED("1인 고정석");

    private final String description;

    StudyCafePassType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static StudyCafePassType indexOf(int index) {
        try{
            return StudyCafePassType.values()[index];
        }catch (Exception e){
            throw new AppException("잘못된 입력입니다.");
        }
    }
}
