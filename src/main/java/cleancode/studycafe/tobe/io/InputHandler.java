package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.model.Pass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;
import java.util.Scanner;

public class InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    public StudyCafePassType getPassTypeSelectingUserAction() {
        String userInput = SCANNER.nextLine();
        int index = Integer.parseInt(userInput);
        return StudyCafePassType.indexOf(index - 1);
    }

    public <E extends Pass> E getSelectPass(List<E> passes) {
        String userInput = SCANNER.nextLine();
        int selectedIndex = Integer.parseInt(userInput) - 1;
        return passes.get(selectedIndex);
    }

    public boolean getLockerSelection() {
        String userInput = SCANNER.nextLine();
        return "1".equals(userInput);
    }

}
