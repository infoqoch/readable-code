package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafePassesFileImpl;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.model.StudyCafePasses;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafePasses studyCafePasses = new StudyCafePassesFileImpl();
    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            StudyCafePassType studyCafePassType = selectPassType();
            StudyCafePass selectedPass = selectPass(studyCafePassType);
            StudyCafeLockerPass lockerPass = askAndSelectLockPass(studyCafePassType, selectedPass);

            outputHandler.showPassOrderSummary(selectedPass, lockerPass);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePass selectPass(StudyCafePassType studyCafePassType) {
        if (studyCafePassType == StudyCafePassType.HOURLY) {
            return askAndSelectPassOf(StudyCafePassType.HOURLY);
        } else if (studyCafePassType == StudyCafePassType.WEEKLY) {
            return askAndSelectPassOf(StudyCafePassType.WEEKLY);
        } else if (studyCafePassType == StudyCafePassType.FIXED) {
            return askAndSelectPassOf(StudyCafePassType.FIXED);
        }
        throw new AppException("잘못된 PassType입니다.");
    }

    private StudyCafeLockerPass askAndSelectLockPass(StudyCafePassType studyCafePassType, StudyCafePass selectedPass) {
        if(studyCafePassType != StudyCafePassType.FIXED) return null;

        StudyCafeLockerPass lockerPass = getStudyCafeLockerPass(selectedPass);
        if(lockerPass == null) return null;

        outputHandler.askLockerPass(lockerPass);
        if (inputHandler.getLockerSelection()) return lockerPass;

        throw new AppException("잘못된 lock 입력입니다.");
    }

    private StudyCafePassType selectPassType() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private StudyCafeLockerPass getStudyCafeLockerPass(StudyCafePass selectedPass) {
        return this.studyCafePasses.getLockerPasses().stream()
            .filter(option ->
                option.getPassType() == selectedPass.getPassType()
                    && option.getDuration() == selectedPass.getDuration()
            )
            .findFirst()
            .orElse(null);
    }

    private StudyCafePass askAndSelectPassOf(StudyCafePassType weekly) {
        List<StudyCafePass> hourlyPasses = getStudyCafePasses(weekly);
        outputHandler.showPassListForSelection(hourlyPasses);
        return inputHandler.getSelectPass(hourlyPasses);
    }

    private List<StudyCafePass> getStudyCafePasses(StudyCafePassType studyCafePassType) {
        return this.studyCafePasses.getStudyCafePasses().stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == studyCafePassType)
                .toList();
    }
}
