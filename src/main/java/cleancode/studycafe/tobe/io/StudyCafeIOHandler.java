package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.order.StudyCafePassOrder;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;

import java.util.List;

public interface StudyCafeIOHandler {
    void showWelcomeMessage();

    void showAnnouncement();

    void showPassOrderSummary(StudyCafePassOrder passOrder);

    void showSimpleMessage(String message);

    StudyCafePassType askPassTypeSelecting();

    StudyCafeSeatPass askPassSelecting(List<StudyCafeSeatPass> passCandidates);

    boolean askLockerPass(StudyCafeLockerPass lockerPassCandidate);
}
