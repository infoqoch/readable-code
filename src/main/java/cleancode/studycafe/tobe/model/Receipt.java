package cleancode.studycafe.tobe.model;

import java.util.Optional;

public class Receipt {
    private final StudyCafePass studyCafePass;
    private final StudyCafeLockerPass lockerPass;

    private Receipt(StudyCafePass studyCafePass, StudyCafeLockerPass lockerPass) {
        this.studyCafePass = studyCafePass;
        this.lockerPass = lockerPass;
    }

    public static Receipt of(StudyCafePass studyCafePass, StudyCafeLockerPass lockerPass) {
        return new Receipt(studyCafePass, lockerPass);
    }

    public static Receipt of(StudyCafePass studyCafePass) {
        return new Receipt(studyCafePass, null);
    }


    public StudyCafePass getStudyCafePass() {
        return studyCafePass;
    }

    public Optional<StudyCafeLockerPass> getLockerPass() {
        return Optional.ofNullable(lockerPass);
    }

    public int getDiscountPrice() {
        return (int) (studyCafePass.getPrice() * studyCafePass.getDiscountRate());
    }

    public int getTotalPrice() {
        return studyCafePass.getPrice()
                - getDiscountPrice()
                + (lockerPass == null ? 0 : lockerPass.getPrice());
    }
}
