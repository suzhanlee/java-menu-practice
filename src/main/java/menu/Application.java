package menu;

import java.util.List;
import java.util.Scanner;
import menu.controller.MenuController;
import menu.domain.Categories;
import menu.domain.asia.AsianCategory;
import menu.domain.china.ChineseCategory;
import menu.domain.japan.JapaneseCategory;
import menu.domain.korea.KoreanCategory;
import menu.domain.recommend.MenuRandomService;
import menu.domain.recommend.RandomCategoryRecommender;
import menu.domain.recommend.RandomMenuRecommender;
import menu.domain.recommend.Recommendation;
import menu.domain.west.WesternCategory;
import menu.view.InputView;
import menu.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        MenuController controller = new MenuController(new InputView(new Scanner(System.in)),
                new Recommendation(createRandomCategoryRecommender(),
                        new RandomMenuRecommender(new MenuRandomService())), new OutputView());

        controller.run();
    }

    private static RandomCategoryRecommender createRandomCategoryRecommender() {
        return new RandomCategoryRecommender(new MenuRandomService(), createCategories());
    }

    private static Categories createCategories() {
        return new Categories(
                List.of(new JapaneseCategory("일식"), new KoreanCategory("한식"), new ChineseCategory("중식"),
                        new AsianCategory("아시안"), new WesternCategory("양식")));
    }
}
