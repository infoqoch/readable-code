package cleancode.studycafe.tobe.model;

import java.util.List;
import java.util.Optional;

public interface StudyCafePassesSupplier {
    List<StudyCafePass> getStudyCafePasses(StudyCafePassType studyCafePassType);

    Optional<StudyCafeLockerPass> getLockerPass(StudyCafePass selectedPass);
}
