package cleancode.studycafe.tobe.model;

import java.util.List;
import java.util.Optional;

// StudyCafePasses의 정보를 받는 로직은 추상화한다.
public interface StudyCafePassesSupplier {
    List<StudyCafePass> getStudyCafePasses(StudyCafePassType studyCafePassType);

    Optional<StudyCafeLockerPass> getLockerPass(StudyCafePass selectedPass);
}
