package menu.domain.recommend;

import java.util.List;
import menu.domain.recommend.checker.RecommendChecker;

public class RecommendationMenusSupervisor {

    private final List<RecommendChecker> recommendCheckers;

    public RecommendationMenusSupervisor(List<RecommendChecker> recommendCheckers) {
        this.recommendCheckers = recommendCheckers;
    }

    public boolean recommendable() {
        return recommendCheckers.stream().allMatch(RecommendChecker::isRecommendable);
    }
}
