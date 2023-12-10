package menu.domain.recommend.checker;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicatedMenusChecker implements RecommendChecker {

    private final List<String> menus;

    public DuplicatedMenusChecker(List<String> menus) {
        this.menus = menus;
    }

    @Override
    public boolean isRecommendable() {
        Set<String> uniqueMenus = new HashSet<>(menus);
        return menus.size() != uniqueMenus.size();
    }
}
