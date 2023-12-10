package menu.domain;

import java.util.List;

public interface RandomService {

    int pickNumberInRange(final int startInclusive, final int endInclusive);

    <T> List<T> shuffle(final List<T> list);
}
