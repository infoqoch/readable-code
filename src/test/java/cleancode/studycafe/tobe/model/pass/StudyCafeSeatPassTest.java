package cleancode.studycafe.tobe.model.pass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudyCafeSeatPassTest {

    @DisplayName("StudyCafeSeatPass의 FIXED 타입을 생성한다. 락커를 사용할 수 없다.")
    @Test
    void of_FIXED(){
        // given, when
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 60, 10000, 0.5);

        // then
        assertThat(pass.getPassType()).isEqualTo(StudyCafePassType.FIXED);
        assertThat(pass.getDuration()).isEqualTo(60);
        assertThat(pass.getPrice()).isEqualTo(10000);
        assertThat(pass.getDiscountPrice()).isEqualTo(5000);
        assertThat(pass.cannotUseLocker()).isFalse();
    }

    @DisplayName("StudyCafeSeatPass의 FIXED 타입을 생성한다. 락커를 사용할 수 있다.")
    @Test
    void of_HOURLY(){
        // given, when
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 10, 1000, 0.9);

        // then
        assertThat(pass.getPassType()).isEqualTo(StudyCafePassType.HOURLY);
        assertThat(pass.getDuration()).isEqualTo(10);
        assertThat(pass.getPrice()).isEqualTo(1000);
        assertThat(pass.getDiscountPrice()).isEqualTo(900);
    }
}