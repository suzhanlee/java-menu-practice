package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class MenuRandomService implements RandomService {

    @Override
    public int pickNumberInRange(int startInclusive, int endInclusive) {
        return Randoms.pickNumberInRange(startInclusive, endInclusive);
    }

    @Override
    public <T> List<T> shuffle(List<T> list) {
        return Randoms.shuffle(list);
    }
}
