package menu;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.Randoms;
import org.junit.jupiter.api.RepeatedTest;

public class RandomsTest {

    @RepeatedTest(30)
    void pickNumberInRange(){
        int result = Randoms.pickNumberInRange(0, 5);
        assertThat(result).isGreaterThanOrEqualTo(0);
        assertThat(result).isLessThanOrEqualTo(5);
    }
}
