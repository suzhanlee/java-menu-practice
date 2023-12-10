package menu.domain;

import java.util.List;

public class Couch {

    private final String name;
    private final List<String> dislikeMenus;

    public Couch(String name, List<String> dislikeMenus) {
        validate(name);
        validateDislikeMenus(dislikeMenus);
        this.name = name;
        this.dislikeMenus = dislikeMenus;
    }

    private void validate(String name) {
        validateName(name);
        validateDislikeMenus(dislikeMenus);
    }

    private void validateName(String name) {
        if (outOfNameSizeRange(name)) {
            throw new IllegalStateException("코치의 이름은 2 ~ 4자리까지 가능합니다.");
        }
    }

    private boolean outOfNameSizeRange(String name) {
        return name.length() < 2 || name.length() > 4;
    }

    private void validateDislikeMenus(List<String> dislikeMenus) {
        if (outOfMenusSizeRange(dislikeMenus)) {
            throw new IllegalStateException("기피 음식은 2개 까지만 지정할 수 있습니다.");
        }
    }

    private boolean outOfMenusSizeRange(List<String> dislikeMenus) {
        return dislikeMenus.size() > 2;
    }

    public boolean isDislike(List<String> menus) {
        return menus.stream().anyMatch(this.dislikeMenus::contains);
    }
}
