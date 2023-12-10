package menu.domain.recommend;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import menu.domain.Category;
import menu.domain.Couch;

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
        for (int i = 0; i < 5; i++) {
            Category category = randomCategoryRecommender.chooseRandomCategory(4);
            String menu = randomMenuRecommender.chooseMenuByCategory(category);
            categoryCountRecorder.put(category, categoryCountRecorder.getOrDefault(category, 0) + 1);
            menus.add(menu);
        }

        try {
            validateRecommendInfo(couch, menus, categoryCountRecorder);
        } catch (IllegalStateException e) {
            return recommend(couch);
        }
        return menus;
    }

    private void validateRecommendInfo(Couch couch, List<String> menus, Map<Category, Integer> categoryCountRecorder) {
        validateMenus(menus);
        validateDislikeMenu(couch, menus);
        validateCategory(categoryCountRecorder);
    }

    private void validateCategory(Map<Category, Integer> categoryCountRecorder) {
        for (Integer categoryCount : categoryCountRecorder.values()) {
            if (categoryCount > 2) {
                throw new IllegalStateException("카테고리 갯수가 2 이상입니다.");
            }
        }
    }

    private void validateMenus(List<String> menus) {
        Set<String> uniqueMenus = new HashSet<>(menus);
        if (menus.size() != uniqueMenus.size()) {
            throw new IllegalStateException("추천 메뉴에 중복 메뉴가 존재하면 안됩니다.");
        }
    }

    private void validateDislikeMenu(Couch couch, List<String> menus) {
        if (couch.isDislike(menus)) {
            throw new IllegalStateException("기피 메뉴를 추천 메뉴에 넣을 수 없습니다.");
        }
    }
}
