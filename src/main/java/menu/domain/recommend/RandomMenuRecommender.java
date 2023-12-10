package menu.domain.recommend;

import java.util.List;
import java.util.Optional;
import menu.domain.Category;

public class RandomMenuRecommender {

    private final RandomService randomService;

    public RandomMenuRecommender(RandomService randomService) {
        this.randomService = randomService;
    }

    public String chooseMenuByCategory(Category category) {
        List<String> shuffledMenus = randomService.shuffle(category.getMenus());
        return Optional.ofNullable(shuffledMenus.get(0))
                .orElseThrow(() -> new IllegalStateException("[ERROR]카테고리에 메뉴가 존재하지 않습니다."));
    }
}
