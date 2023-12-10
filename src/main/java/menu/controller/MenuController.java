package menu.controller;

import java.util.ArrayList;
import java.util.List;
import menu.domain.Couch;
import menu.domain.recommend.Recommendation;
import menu.dto.IndividualRecommendMenu;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuController {

    private final InputView inputView;
    private final Recommendation recommendation;
    private final OutputView outputView;

    public MenuController(InputView inputView, Recommendation recommendation, OutputView outputView) {
        this.inputView = inputView;
        this.recommendation = recommendation;
        this.outputView = outputView;
    }

    public void run() {
        List<Couch> couches = getCouchesWithRetry();
        List<IndividualRecommendMenu> individualRecommendMenus = getIndividualRecommendMenusWithRetry(couches);
        outputView.printRecommendationResult(individualRecommendMenus);
    }

    private List<Couch> getCouchesWithRetry() {
        try {
            return getCouches();
        } catch (IllegalStateException e) {
            outputView.printErrorMessage(e.getMessage());
            return getCouchesWithRetry();
        }
    }

    private List<Couch> getCouches() {
        List<String> couchNames = inputView.couchNames();
        List<Couch> couches = new ArrayList<>();
        for (String couchName : couchNames) {
            List<String> dislikeMenus = inputView.dislikeMenus(couchName);
            Couch couch = new Couch(couchName, dislikeMenus);
            couches.add(couch);
        }
        return couches;
    }

    private List<IndividualRecommendMenu> getIndividualRecommendMenusWithRetry(List<Couch> couches) {
        try {
            return getIndividualRecommendMenus(couches);
        } catch (IllegalStateException e) {
            outputView.printErrorMessage(e.getMessage());
            return getIndividualRecommendMenusWithRetry(couches);
        }
    }

    private List<IndividualRecommendMenu> getIndividualRecommendMenus(List<Couch> couches) {
        List<IndividualRecommendMenu> individualRecommendMenus = new ArrayList<>();
        for (Couch couch : couches) {
            List<String> recommendMenus = recommendation.recommend(couch);
            individualRecommendMenus.add(new IndividualRecommendMenu(couch, recommendMenus));
        }
        return individualRecommendMenus;
    }
}
