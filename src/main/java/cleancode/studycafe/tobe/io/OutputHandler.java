package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.*;

import java.util.List;

public class OutputHandler {

    public void showWelcomeMessage() {
        System.out.println("*** 프리미엄 스터디카페 ***");
    }

    public void showAnnouncement() {
        System.out.println("* 사물함은 고정석 선택 시 이용 가능합니다. (추가 결제)");
        System.out.println("* !오픈 이벤트! 2주권 이상 결제 시 10% 할인, 12주권 결제 시 15% 할인! (결제 시 적용)");
        System.out.println();
    }

    public void askPassTypeSelection() {
        System.out.println("사용하실 이용권을 선택해 주세요.");
        for (StudyCafePassType value : StudyCafePassType.values()) {
            System.out.printf("%s. %s | ", value.ordinal() + 1, value.getDescription());
        }
        System.out.println();
    }

    public void showPassListForSelection(List<? extends Pass> passes) {
        System.out.println();
        System.out.println("이용권 목록");
        for (int index = 0; index < passes.size(); index++) {
            Pass pass = passes.get(index);
            System.out.println(String.format("%s. ", index + 1) + display(pass));
        }
    }

    public void askLockerPass(StudyCafeLockerPass lockerPass) {
        System.out.println();
        String askMessage = String.format(
            "사물함을 이용하시겠습니까? (%s)",
            display(lockerPass)
        );

        System.out.println(askMessage);
        System.out.println("1. 예 | 2. 아니오");
    }

    public void showPassOrderSummary(Receipt receipt) {
        System.out.println();
        System.out.println("이용 내역");
        System.out.println("이용권: " + display(receipt.getStudyCafePass()));
        receipt.getLockerPass().ifPresent(l -> System.out.println("사물함: " + display(l)));

        int discountPrice = receipt.getDiscountPrice();
        if (discountPrice > 0) {
            System.out.println("이벤트 할인 금액: " + discountPrice + "원");
        }

        int totalPrice = receipt.getTotalPrice();
        System.out.println("총 결제 금액: " + totalPrice + "원");
        System.out.println();
    }

    public void showSimpleMessage(String message) {
        System.out.println(message);
    }

    private String display(Pass pass) {
        if (pass.isTypeOf(StudyCafePassType.HOURLY)) {
            return String.format("%s시간권 - %d원", pass.getDuration(), pass.getPrice());
        }
        if (pass.isTypeOf(StudyCafePassType.WEEKLY)) {
            return String.format("%s주권 - %d원", pass.getDuration(), pass.getPrice());
        }
        if (pass.isTypeOf(StudyCafePassType.FIXED)) {
            return String.format("%s주권 - %d원", pass.getDuration(), pass.getPrice());
        }
        return "";
    }
}
