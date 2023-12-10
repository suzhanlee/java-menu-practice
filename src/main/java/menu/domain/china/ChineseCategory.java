package menu.domain.china;

import java.util.ArrayList;
import java.util.List;
import menu.domain.Category;

public class ChineseCategory implements Category {

    private final String name;
    private List<String> menus = new ArrayList<>();

    {
        menus.addAll(List.of("깐풍기", "볶음면", "동파육", "짜장면", "짬뽕", "마파두부", "탕수육", "토마토 달걀볶음", "고추잡채"));
    }

    public ChineseCategory(String name) {
        this.name = name;
    }

    @Override
    public List<String> getMenus() {
        return this.menus;
    }
}
