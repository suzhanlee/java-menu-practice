package menu.domain.recommend;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import menu.domain.Category;
import menu.domain.Couch;
import menu.domain.recommend.checker.DislikedMenuChecker;
import menu.domain.recommend.checker.DuplicatedMenusChecker;
import menu.domain.recommend.checker.ExcessiveCategoryDuplicationChecker;

public class Recommendation {

    private final RandomCategoryRecommender randomCategoryRecommender;
    private final RandomMenuRecommender randomMenuRecommender;

    public Recommendation(RandomCategoryRecommender randomCategoryRecommender,
                          RandomMenuRecommender randomMenuRecommender) {
        this.randomCategoryRecommender = randomCategoryRecommender;
        this.randomMenuRecommender = randomMenuRecommender;
    }

    public List<String> recommend(Couch couch) {
        Map<Category, Integer> categoryCountRecorder = new LinkedHashMap<>();
        List<String> menus = new ArrayList<>();
        for (int recommendationCount = 0; recommendationCount < 5; recommendationCount++) {
            Category category = randomCategoryRecommender.chooseRandomCategory(4);
            String menu = randomMenuRecommender.chooseMenuByCategory(category);
            categoryCountRecorder.put(category, categoryCountRecorder.getOrDefault(category, 0) + 1);
            menus.add(menu);
        }

        return getRecommendationMenus(couch, menus, categoryCountRecorder);
    }

    private List<String> getRecommendationMenus(Couch couch, List<String> menus,
                                                Map<Category, Integer> categoryCountRecorder) {
        RecommendationMenusSupervisor menusSupervisor = new RecommendationMenusSupervisor(
                List.of(
                        new ExcessiveCategoryDuplicationChecker(categoryCountRecorder),
                        new DuplicatedMenusChecker(menus),
                        new DislikedMenuChecker(couch, menus)
                )
        );
        if (menusSupervisor.recommendable()) {
            return menus;
        }
        return recommend(couch);
    }
}
