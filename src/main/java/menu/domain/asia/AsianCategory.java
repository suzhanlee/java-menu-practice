package menu.domain.asia;

import java.util.ArrayList;
import java.util.List;
import menu.domain.Category;

public class AsianCategory implements Category {

    private final String name;
    private final List<String> menus = new ArrayList<>();

    {
        menus.addAll(List.of("팟타이", "카오 팟", "나시고렝", "파인애플 볶음밥", "쌀국수", "똠얌꿍", "반미", "월남쌈", "분짜"));
    }

    public AsianCategory(String name) {
        this.name = name;
    }

    @Override
    public List<String> getMenus() {
        return this.menus;
    }
}
