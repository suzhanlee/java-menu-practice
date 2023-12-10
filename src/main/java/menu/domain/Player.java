package menu.domain;

import java.util.ArrayList;
import java.util.List;
import menu.domain.recommend.Recommendation;
import menu.dto.IndividualRecommendMenu;

public class Player {

    private final List<Couch> couches;
    private final Recommendation recommendation;

    public Player(List<Couch> couches, Recommendation recommendation) {
        validateCouches(couches);
        this.couches = couches;
        this.recommendation = recommendation;
    }

    private void validateCouches(List<Couch> couches) {
        if (outOfRange(couches)) {
            throw new IllegalStateException("[ERROR]코치는 최소 2명, 최대 5명까지 식사를 함께 해야 한다.");
        }
    }

    private boolean outOfRange(List<Couch> couches) {
        return couches.size() < 2 || couches.size() > 5;
    }

    public List<IndividualRecommendMenu> createIndividualRecommendMenus() {
        List<IndividualRecommendMenu> individualRecommendMenus = new ArrayList<>();
        for (Couch couch : this.couches) {
            List<String> recommendMenus = recommendation.recommend(couch);
            individualRecommendMenus.add(new IndividualRecommendMenu(couch, recommendMenus));
        }
        return individualRecommendMenus;
    }
}
