package menu.domain.korea;

import java.util.ArrayList;
import java.util.List;

public class KoreanCategory {

    private final String name;
    private final List<String> menus = new ArrayList<>();

    {
        menus.addAll(List.of("김밥", "김치찌개", "쌈밥", "된장찌개", "비빔밥", "칼국수", "불고기", "떡볶이", "제육볶음"));
    }

    public KoreanCategory(String name) {
        this.name = name;
    }
}
