package menu.controller;

import java.util.List;
import menu.domain.Couch;

public record IndividualRecommendMenu(Couch couch, List<String> menus) {

}
