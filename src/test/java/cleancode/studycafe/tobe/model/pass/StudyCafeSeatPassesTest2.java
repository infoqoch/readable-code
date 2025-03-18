package cleancode.studycafe.tobe.model.pass;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class StudyCafeSeatPassesTest2 {

    @DisplayName("입력한 데이터에 따라 findPassBy가 각 타입별로 검색한다.")
    @Test
    void findPassBy() {
        // given
        StudyCafeSeatPass h1 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 1, 10, 0.1);
        StudyCafeSeatPass h2 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 11, 110, 0.11);
        StudyCafeSeatPass w1 = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 2, 20, 0.2);
        StudyCafeSeatPass w2 = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 22, 220, 0.22);
        StudyCafeSeatPass f1 = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 3, 30, 0.3);
        StudyCafeSeatPass f2 = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 33, 330, 0.33);

        // empty
        assertFindPassBy(0, 0, 0);
        // only Hourly
        assertFindPassBy(1, 0, 0, h1);
        // only Weekly
        assertFindPassBy(0, 1, 0, w1);
        // only Fixed
        assertFindPassBy(0, 0, 1, f1);

        // mixed
        assertFindPassBy(1, 1, 1, h1, w1, f1);
        assertFindPassBy(2, 2, 2, h1, w1, f1, h2, w2, f2);
        assertFindPassBy(2, 1, 1, h1, w1, h2, f2);
    }

    private static void assertFindPassBy(int hourlySize, int weeklySize, int fixedSize, StudyCafeSeatPass...passes) {
        // given
        List<StudyCafeSeatPass> list = Arrays.stream(passes).toList();
        StudyCafeSeatPasses studyCafeSeatPasses = StudyCafeSeatPasses.of(list);

        // when - then : findPassBy(HOURLY)
        List<StudyCafeSeatPass> f1 = studyCafeSeatPasses.findPassBy(StudyCafePassType.HOURLY);
        Assertions.assertThat(f1).size().isEqualTo(hourlySize);

        // when - then : findPassBy(WEEKLY)
        List<StudyCafeSeatPass> f2 = studyCafeSeatPasses.findPassBy(StudyCafePassType.WEEKLY);
        Assertions.assertThat(f2).size().isEqualTo(weeklySize);

        // when - then : findPassBy(FIXED)
        List<StudyCafeSeatPass> f3 = studyCafeSeatPasses.findPassBy(StudyCafePassType.FIXED);
        Assertions.assertThat(f3).size().isEqualTo(fixedSize);
    }
}