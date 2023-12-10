package menu.controller;

import java.util.ArrayList;
import java.util.List;
import menu.domain.Couch;
import menu.domain.Player;
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
        Player player = getCouchesWithRetry();
        List<IndividualRecommendMenu> individualRecommendMenus = getIndividualRecommendMenusWithRetry(player);
        outputView.printRecommendationResult(individualRecommendMenus);
    }

    private Player getCouchesWithRetry() {
        try {
            return getCouches();
        } catch (IllegalStateException e) {
            outputView.printErrorMessage(e.getMessage());
            return getCouchesWithRetry();
        }
    }

    private Player getCouches() {
        List<String> couchNames = inputView.couchNames();
        List<Couch> couches = new ArrayList<>();
        for (String couchName : couchNames) {
            List<String> dislikeMenus = inputView.dislikeMenus(couchName);
            Couch couch = new Couch(couchName, dislikeMenus);
            couches.add(couch);
        }
        return new Player(couches, recommendation);
    }

    private List<IndividualRecommendMenu> getIndividualRecommendMenusWithRetry(Player player) {
        try {
            return getIndividualRecommendMenus(player);
        } catch (IllegalStateException e) {
            outputView.printErrorMessage(e.getMessage());
            return getIndividualRecommendMenusWithRetry(player);
        }
    }

    private List<IndividualRecommendMenu> getIndividualRecommendMenus(Player player) {
        return player.createIndividualRecommendMenus();
    }
}
