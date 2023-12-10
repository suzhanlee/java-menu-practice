package menu.domain.japan;

import java.util.ArrayList;
import java.util.List;

public class JapaneseCategory {

    private final String name;
    private final List<String> menus = new ArrayList<>();

    {
        menus.addAll(List.of("규동", "우동", "미소시루", "스시", "가츠동", "오니기리", "하이라이스", "라멘", "오코노미야끼"));
    }

    public JapaneseCategory(String name) {
        this.name = name;
    }
}
