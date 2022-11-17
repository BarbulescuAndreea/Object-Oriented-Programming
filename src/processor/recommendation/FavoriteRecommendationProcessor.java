package processor.recommendation;

import fileio.ActionInputData;
import fileio.UserInputData;
import inputdata.InputOutputUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class FavoriteRecommendationProcessor extends RecommandationProcessor {
    @Override
    public void processAction(final ActionInputData actionInputData) {
        String username = actionInputData.getUsername();
        UserInputData userInputData = getUserForUsername(username);
        if (userInputData == null) {
            return;
        }
        if (!userInputData.getSubscriptionType().equals("PREMIUM")) {
            InputOutputUtils.getShared().writeFile(actionInputData.getActionId(),
                    "FavoriteRecommendation cannot be applied!");
            return;
        }

        List<UserInputData> userInputDataList = InputOutputUtils.getShared().getInput().getUsers();

        Map<String, Integer> favoriteMap = new HashMap<>();
        for (UserInputData user : userInputDataList) {
            if (user.getUsername().equals(username)) {
                continue;
            }

            for (String favoriteMovie : user.getFavoriteMovies()) {
                favoriteMap.put(favoriteMovie, favoriteMap.getOrDefault(favoriteMovie, 0) + 1);
            }
        }
        //am filtrat pentru a intoarce video care este cel mai vazut,
        //apoi cu findfirst() l am luat pe primul pentru
        //a respecta criteriul de ordinea de aparitie din baza de date
        String recommendedTitle =
                favoriteMap.entrySet()
                .stream()
                .sorted(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .filter(title -> !userInputData.getHistory().containsKey(title))
                .findFirst().orElse(null);


        String message = recommendedTitle != null
                ? String.format("FavoriteRecommendation result: %s", recommendedTitle)
                : "FavoriteRecommendation cannot be applied!";

        InputOutputUtils.getShared().writeFile(actionInputData.getActionId(), message);

    }
}
