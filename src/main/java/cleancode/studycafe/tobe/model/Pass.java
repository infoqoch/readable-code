package cleancode.studycafe.tobe.model;

public interface Pass {
    StudyCafePassType getPassType();

    int getDuration();

    int getPrice();

    boolean isTypeOf(StudyCafePassType studyCafePassType);

    DurationType getDurationType();
}
