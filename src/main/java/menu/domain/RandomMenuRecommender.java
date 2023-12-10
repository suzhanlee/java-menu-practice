package menu.domain;

import java.util.List;
import java.util.Optional;

public class RandomMenuRecommender {

    private final RandomService randomService;
    private final Categories categories;

    public RandomMenuRecommender(RandomService randomService, Categories categories) {
        this.randomService = randomService;
        this.categories = categories;
    }

    public String chooseMenuByCategory(int number) {
        Category category = chooseRandomCategory(number);
        List<String> shuffledMenus = randomService.shuffle(category.getMenus());
        return Optional.ofNullable(shuffledMenus.get(0))
                .orElseThrow(() -> new IllegalStateException("카테고리에 메뉴가 존재하지 않습니다."));
    }

    private Category chooseRandomCategory(int number) {
        return categories.findCategory(randomService.pickNumberInRange(1, number));
    }
}
