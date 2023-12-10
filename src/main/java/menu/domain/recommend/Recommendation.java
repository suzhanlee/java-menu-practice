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
        Map<Category, Integer> categoryCountRecorder = getCategoryCountRecorder();
        List<String> menus = getMenus(categoryCountRecorder);
        return getRecommendationMenus(couch, menus, categoryCountRecorder);
    }

    private Map<Category, Integer> getCategoryCountRecorder() {
        Map<Category, Integer> categoryCountRecorder = new LinkedHashMap<>();
        for (int recommendationCount = 0; recommendationCount < 5; recommendationCount++) {
            Category category = randomCategoryRecommender.chooseRandomCategory(4);
            categoryCountRecorder.put(category, categoryCountRecorder.getOrDefault(category, 0) + 1);
        }
        return categoryCountRecorder;
    }

    private List<String> getMenus(Map<Category, Integer> categoryCountRecorder) {
        List<String> menus = new ArrayList<>();
        for (Category category : categoryCountRecorder.keySet()) {
            menus.add(randomMenuRecommender.chooseMenuByCategory(category));
        }
        return menus;
    }

    private List<String> getRecommendationMenus(Couch couch, List<String> menus, Map<Category, Integer> categoryCountRecorder) {
        if (createRecommendationMenus(couch, menus,
                categoryCountRecorder).recommendable()) {
            return menus;
        }
        return recommend(couch);
    }

    private RecommendationMenusSupervisor createRecommendationMenus(Couch couch, List<String> menus, Map<Category, Integer> categoryCountRecorder) {
        return new RecommendationMenusSupervisor(
                List.of(
                        new ExcessiveCategoryDuplicationChecker(categoryCountRecorder),
                        new DuplicatedMenusChecker(menus),
                        new DislikedMenuChecker(couch, menus)
                )
        );
    }
}
