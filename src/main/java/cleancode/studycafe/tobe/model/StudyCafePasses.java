package cleancode.studycafe.tobe.model;

import java.util.List;

// StudyCafePasses의 정보를 받는 로직은 추상화한다.
public interface StudyCafePasses {
    List<StudyCafePass> getStudyCafePasses();

    List<StudyCafeLockerPass> getLockerPasses();
}
