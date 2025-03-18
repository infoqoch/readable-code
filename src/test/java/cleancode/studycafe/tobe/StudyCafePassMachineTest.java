package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.StudyCafeIOHandler;
import cleancode.studycafe.tobe.model.order.StudyCafePassOrder;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPasses;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.provider.LockerPassProvider;
import cleancode.studycafe.tobe.provider.SeatPassProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafePassMachineTest {

    @DisplayName("적합한 FIXED와 LOCKER가 제공되는 상황에서, FIXED 좌석과 LOCKER를 선택하여 정상 처리한다.")
    @Test
    void select_FIXEDAndLocker(){
        // given
        List<StudyCafePassOrder> spy = new ArrayList<>();
        SeatPassProvider seatPassProvider = new SeatPassProvider() {
            @Override
            public StudyCafeSeatPasses getSeatPasses() {
                return StudyCafeSeatPasses.of(List.of(
                        StudyCafeSeatPass.of(StudyCafePassType.FIXED, 10, 1000, 0.1)
                ));
            }
        };

        LockerPassProvider lockerPassProvider = new LockerPassProvider() {
            @Override
            public StudyCafeLockerPasses getLockerPasses() {
                return StudyCafeLockerPasses.of(List.of(
                        StudyCafeLockerPass.of(StudyCafePassType.FIXED, 10, 500)
                ));
            }
        };

        StudyCafeIOHandler studyCafeIOHandler = new StudyCafeIOHandler() {
            @Override
            public void showWelcomeMessage() {}

            @Override
            public void showAnnouncement() {}

            @Override
            public void showSimpleMessage(String message) {}

            @Override
            public StudyCafePassType askPassTypeSelecting() {
                return StudyCafePassType.FIXED; // FIXED 타입을 선택한다.
            }

            @Override
            public StudyCafeSeatPass askPassSelecting(List<StudyCafeSeatPass> passCandidates) {
                return passCandidates.get(0); // FIXED seatPass 중 첫 번째를 선택한다. 애당초 하나만 존재하지만!
            }

            @Override
            public boolean askLockerPass(StudyCafeLockerPass lockerPassCandidate) {
                return true; // LOCKER Pass가 존재하며 선택한다.
            }

            @Override
            public void showPassOrderSummary(StudyCafePassOrder passOrder) {
                spy.add(passOrder);
            }

        };

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(seatPassProvider, lockerPassProvider, studyCafeIOHandler);

        // when
        studyCafePassMachine.run();

        // then
        assertThat(spy).size().isEqualTo(1);
        StudyCafePassOrder savedOrder = spy.get(0);

        assertThat(savedOrder.getLockerPass()).isPresent(); // LOCKER Pass가 존재하며 선택하였다.
        assertThat(savedOrder.getSeatPass().getPassType()).isEqualTo(StudyCafePassType.FIXED); // FIXED Pass가 선택되었다.
        assertThat(savedOrder.getTotalPrice()).isEqualTo(1400); // (1000 - 100) + 500 = 1400
    }
}