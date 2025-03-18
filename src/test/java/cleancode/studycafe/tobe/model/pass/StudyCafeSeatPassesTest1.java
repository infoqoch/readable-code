package cleancode.studycafe.tobe.model.pass;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class StudyCafeSeatPassesTest1 {
    @DisplayName("findPassBy으로 존재하는 타입으로 검색하면, 해당 값을 잘 가져온다.")
    @Test
    void findPassBy1() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 1, 1000, 0.5);
        StudyCafeSeatPasses studyCafeSeatPasses = StudyCafeSeatPasses.of(List.of(seatPass));

        // when
        List<StudyCafeSeatPass> found = studyCafeSeatPasses.findPassBy(StudyCafePassType.HOURLY);

        // then
        Assertions.assertThat(found).containsOnly(seatPass);
    }

    @DisplayName("findPassBy으로 존재하지 않는 타입으로 검색하면, 값을 가져오지 않는다.")
    @Test
    void findPassBy2() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 1, 1000, 0.5);
        StudyCafeSeatPasses studyCafeSeatPasses = StudyCafeSeatPasses.of(List.of(seatPass));

        // when
        List<StudyCafeSeatPass> found = studyCafeSeatPasses.findPassBy(StudyCafePassType.WEEKLY);

        // then
        Assertions.assertThat(found).isEmpty();
    }

    @DisplayName("findPassBy으로 두 개 존재하는 타입으로 검색하면, 두 개를 다 가져온다.")
    @Test
    void findPassBy3() {
        // given
        StudyCafeSeatPass hourly1 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 1, 1000, 0.5);
        StudyCafeSeatPass hourly2 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 2, 2000, 0.3);
        StudyCafeSeatPasses studyCafeSeatPasses = StudyCafeSeatPasses.of(List.of(hourly1, hourly2));

        // when
        List<StudyCafeSeatPass> found = studyCafeSeatPasses.findPassBy(StudyCafePassType.HOURLY);

        // then
        Assertions.assertThat(found).containsOnly(hourly1, hourly2);
    }

    @DisplayName("StudyCafeSeatPasses에 여러 종류가 섞여 있으면, findPassBy으로 조회한 타입만 정확하게 가져온다.")
    @Test
    void findPassBy4() {
        // given
        StudyCafeSeatPass hourly1 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 1, 1000, 0.1);
        StudyCafeSeatPass hourly2 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 2, 2000, 0.2);
        StudyCafeSeatPass weekly = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 3, 3000, 0.3);
        StudyCafeSeatPass fixed = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 4000, 0.4);
        StudyCafeSeatPasses studyCafeSeatPasses = StudyCafeSeatPasses.of(List.of(hourly1,hourly2, weekly, fixed));

        // when
        List<StudyCafeSeatPass> found = studyCafeSeatPasses.findPassBy(StudyCafePassType.HOURLY);

        // then
        Assertions.assertThat(found).containsOnly(hourly1, hourly2);
    }
}