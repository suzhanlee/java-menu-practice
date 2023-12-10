package menu.domain;

import java.util.List;
import java.util.Optional;

public class Categories {

    private final List<Category> categories;

    public Categories(List<Category> categories) {
        this.categories = categories;
    }

    public Category findCategory(int number) {
        return Optional.ofNullable(categories.get(number))
                .orElseThrow(() -> new IllegalStateException("원하는 숫자에 맞는 카테고리가 존재하지 않습니다."));
    }
}
