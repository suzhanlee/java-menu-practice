package menu.domain.recommend.checker;

import java.util.Map;
import menu.domain.Category;

public class ExcessiveCategoryDuplicationChecker implements RecommendChecker{

    private final Map<Category, Integer> categoryCountRecorder;

    public ExcessiveCategoryDuplicationChecker(Map<Category, Integer> categoryCountRecorder) {
        this.categoryCountRecorder = categoryCountRecorder;
    }

    @Override
    public boolean isRecommendable() {
        return categoryCountRecorder.values().stream().anyMatch(categoryCount -> categoryCount > 2);
    }
}
