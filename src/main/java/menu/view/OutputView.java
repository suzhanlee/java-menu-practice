package menu.view;

import java.util.List;
import menu.dto.IndividualRecommendMenu;

public class OutputView {

    public void startMenuRecommendationMessage() {
        System.out.println("점심 메뉴 추천을 시작합니다.\n");
    }

    public void printRecommendationResult(List<IndividualRecommendMenu> individualRecommendMenus) {
        System.out.println("메뉴 추천 결과입니다.");
        System.out.println("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]\n"
                + "[ 카테고리 | 한식 | 한식 | 일식 | 중식 | 아시안 ]");

        for (IndividualRecommendMenu recommendMenu : individualRecommendMenus) {
            StringBuilder recommendMenuBuilder = new StringBuilder();
            for (String menu : recommendMenu.menus()) {
                recommendMenuBuilder.append(" | ").append(menu);
            }
            System.out.printf("\n[ " + recommendMenu.couch().getName() + recommendMenuBuilder + " ]");
        }

        System.out.println("추천을 완료했습니다.");
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
