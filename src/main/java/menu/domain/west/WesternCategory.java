package menu.domain.west;

import java.util.ArrayList;
import java.util.List;
import menu.domain.Category;

public class WesternCategory implements Category {

    private final String name;
    private final List<String> menus = new ArrayList<>();

    {
        menus.addAll(List.of("라자냐", "그라탱", "뇨끼", "끼슈", "프렌치 토스트", "바게트", "스파게티", "피자", "파니니"));
    }

    public WesternCategory(String name) {
        this.name = name;
    }

    @Override
    public List<String> getMenus() {
        return this.menus;
    }
}
