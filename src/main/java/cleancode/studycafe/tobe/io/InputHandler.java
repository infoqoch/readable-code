package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.model.Pass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;
import java.util.Scanner;

public class InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    public StudyCafePassType getPassTypeSelectingUserAction() {
        int index = getAndValidateIndex(StudyCafePassType.values().length);
        return StudyCafePassType.indexOf(index);
    }

    public <E extends Pass> E getSelectPass(List<E> passes) {
        int index = getAndValidateIndex(passes.size());
        return passes.get(index);
    }

    public boolean getLockerSelection() {
        String userInput = SCANNER.nextLine();
        if("1".equals(userInput)) return true;
        if("2".equals(userInput)) return false;
        throw new AppException("잘못된 입력입니다.");
    }

    private static int getAndValidateIndex(int length) {
        String userInput = SCANNER.nextLine();
        int parseInt = Integer.parseInt(userInput);
        int index = IndexSupplier.getInputIndex(parseInt);

        if (index < 0 || index >= length) {
            throw new AppException("잘못된 입력입니다.");
        }
        return index;
    }
}
