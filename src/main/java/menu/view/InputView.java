package menu.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void startMenuRecommendationMessage() {
        System.out.println("점심 메뉴 추천을 시작합니다.\n");
    }

    public List<String> couchNames() {
        System.out.println("코치의 이름을 입력해 주세요. (, 로 구분)");
        return Arrays.stream(scanner.nextLine().split(",")).collect(Collectors.toList());
    }

    public List<String> dislikeMenus(String name) {
        System.out.printf("\n%s(이)가 못 먹는 메뉴를 입력해 주세요.\n", name);
        return Arrays.stream(scanner.nextLine().split(",")).collect(Collectors.toList());
    }
}
