package menu.domain.recommend;

import menu.domain.Categories;
import menu.domain.Category;

public class RandomCategoryRecommender {

    private final RandomService randomService;
    private final Categories categories;

    public RandomCategoryRecommender(RandomService randomService, Categories categories) {
        this.randomService = randomService;
        this.categories = categories;
    }

    public Category chooseRandomCategory(int number) {
        return categories.findCategory(randomService.pickNumberInRange(0, number));
    }
}
