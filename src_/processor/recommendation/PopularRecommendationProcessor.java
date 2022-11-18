package processor.recommendation;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import inputdata.InputOutputUtils;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Comparator;


public final class PopularRecommendationProcessor extends RecommandationProcessor {
    @Override
    public void processAction(final ActionInputData actionInputData) {
        String username = actionInputData.getUsername();
        UserInputData userInputData = getUserForUsername(username);
        if (userInputData == null) {
            InputOutputUtils.getShared().writeFile(actionInputData.getActionId(),
                    "PopularRecommendation cannot be applied!");
            return;
        }
        if (!userInputData.getSubscriptionType().equals("PREMIUM")) {
            InputOutputUtils.getShared().writeFile(actionInputData.getActionId(),
                    "PopularRecommendation cannot be applied!");
            return;
        }

        List<MovieInputData> movies = InputOutputUtils.getShared().getInput().getMovies();
        List<SerialInputData> serials = InputOutputUtils.getShared().getInput().getSerials();

        Map<String, Integer> genrePopularityMap = new HashMap<>();
        for (MovieInputData movieInputData : movies) {
            List<String> genres = movieInputData.getGenres();
            for (String genre : genres) {
                genrePopularityMap.put(genre, genrePopularityMap.getOrDefault(genre, 0) + 1);
            }
        }

        for (SerialInputData serialInputData : serials) {
            List<String> genres = serialInputData.getGenres();
            for (String genre : genres) {
                genrePopularityMap.put(genre, genrePopularityMap.getOrDefault(genre, 0) + 1);
            }
        }

        // sortam dupa popularitate folosing genrePopularityMap
        List<String> genresList = new ArrayList<>(genrePopularityMap.keySet());
        genresList.sort(new Comparator<String>() {
            @Override
            public int compare(final String o1, final String o2) {
                return Integer.compare(
                        genrePopularityMap.get(o1),
                        genrePopularityMap.get(o2)
                )  * -1; // -1 pentru a sorta descrescator
            }
        });



        for (String popularGenre : genresList) {
            for (SerialInputData serialInputData : serials) {
                if (serialInputData.getGenres().contains(popularGenre)
                        && !userInputData.getHistory().containsKey(serialInputData.getTitle())) {
                    String message = String.format("PopularRecommendation result: %s",
                            serialInputData.getTitle());
                    InputOutputUtils.getShared().writeFile(actionInputData.getActionId(), message);
                    return;
                }
            }

            for (MovieInputData movieInputData : movies) {
                if (movieInputData.getGenres().contains(popularGenre)
                        && !userInputData.getHistory().containsKey(movieInputData.getTitle())) {
                    String message = String.format("PopularRecommendation result: %s",
                            movieInputData.getTitle());
                    InputOutputUtils.getShared().writeFile(actionInputData.getActionId(), message);
                    return;
                }
            }
        }

        InputOutputUtils.getShared().writeFile(actionInputData.getActionId(),
                "PopularRecommendation cannot be applied!");
    }
}
