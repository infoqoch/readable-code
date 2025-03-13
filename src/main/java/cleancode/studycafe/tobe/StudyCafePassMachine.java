package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.model.StudyCafePassesSupplier;

import java.util.List;
import java.util.Optional;

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

            StudyCafePassType studyCafePassType = askAndSelectPassType();
            StudyCafePass selectedPass = askAndSelectPassBy(studyCafePassType);
            StudyCafeLockerPass lockerPass = findMatchedLockPassBy(selectedPass)
                    .filter(this::doesWantToSelectLockPass)
                    .orElse(null);

            outputHandler.showPassOrderSummary(selectedPass, lockerPass);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePass askAndSelectPassBy(StudyCafePassType studyCafePassType) {
        if (studyCafePassType == StudyCafePassType.HOURLY) {
            return askAndSelectPassOf(StudyCafePassType.HOURLY);
        } else if (studyCafePassType == StudyCafePassType.WEEKLY) {
            return askAndSelectPassOf(StudyCafePassType.WEEKLY);
        } else if (studyCafePassType == StudyCafePassType.FIXED) {
            return askAndSelectPassOf(StudyCafePassType.FIXED);
        }
        throw new AppException("잘못된 PassType입니다.");
    }

    private boolean doesWantToSelectLockPass(StudyCafeLockerPass lockerPass) {
        outputHandler.askLockerPass(lockerPass);
        return inputHandler.getLockerSelection();
    }

    private Optional<StudyCafeLockerPass> findMatchedLockPassBy(StudyCafePass selectedPass) {
        if(selectedPass.isNotTypeOf(StudyCafePassType.FIXED)) return Optional.empty();
        return studyCafePassesSupplier.getLockerPass(selectedPass);
    }

    private StudyCafePassType askAndSelectPassType() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private StudyCafePass askAndSelectPassOf(StudyCafePassType passType) {
        List<StudyCafePass> passes = getStudyCafePasses(passType);
        outputHandler.showPassListForSelection(passes);
        return inputHandler.getSelectPass(passes);
    }

    private List<StudyCafePass> getStudyCafePasses(StudyCafePassType studyCafePassType) {
        return studyCafePassesSupplier.getStudyCafePasses(studyCafePassType);
    }
}
