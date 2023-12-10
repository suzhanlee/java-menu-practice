package menu.domain.recommend.checker;

import java.util.List;
import menu.domain.Couch;

public class DislikedMenuChecker implements RecommendChecker {

    private final Couch couch;
    private final List<String> menus;

    public DislikedMenuChecker(Couch couch, List<String> menus) {
        this.couch = couch;
        this.menus = menus;
    }

    @Override
    public boolean isRecommendable() {
        return couch.isDislike(menus);
    }
}
