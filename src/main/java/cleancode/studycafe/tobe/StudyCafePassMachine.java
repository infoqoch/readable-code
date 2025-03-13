package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.model.*;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final StudyCafePassesSupplier studyCafePassesSupplier;

    public StudyCafePassMachine(StudyCafePassMachineConfig config) {
        this.studyCafePassesSupplier = config.getStudyCafePassesSupplier();
        this.inputHandler = config.getInputHandler();
        this.outputHandler = config.getOutputHandler();
    }

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            StudyCafePassType studyCafePassType = askAndSelectPass();
            StudyCafePass selectedPass = askAndSelectPassOf(studyCafePassType);
            StudyCafeLockerPass selectedLockerPass = studyCafePassesSupplier.getLockerPass(selectedPass)
                    .filter(this::doesWantToSelectLockPass)
                    .orElse(null);

            Receipt receipt = Receipt.of(selectedPass, selectedLockerPass);
            outputHandler.showPassOrderSummary(receipt);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private boolean doesWantToSelectLockPass(StudyCafeLockerPass lockerPass) {
        outputHandler.askLockerPass(lockerPass);
        return inputHandler.getLockerSelection();
    }

    private StudyCafePassType askAndSelectPass() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private StudyCafePass askAndSelectPassOf(StudyCafePassType passType) {
        List<StudyCafePass> passes = studyCafePassesSupplier.getStudyCafePasses(passType);
        outputHandler.showPassListForSelection(passes);
        return inputHandler.getSelectPass(passes);
    }

}
