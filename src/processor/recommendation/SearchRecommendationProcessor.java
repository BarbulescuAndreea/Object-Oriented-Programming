package processor.recommendation;


import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import inputdata.InputOutputUtils;
import store.RatingStore;


import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.stream.Collectors;

public final class SearchRecommendationProcessor extends RecommandationProcessor {
    @Override
    public void processAction(final ActionInputData actionInputData) {
        String username = actionInputData.getUsername();
        UserInputData userInputData = getUserForUsername(username);
        if (userInputData == null) {
            return;
        }
        if (!userInputData.getSubscriptionType().equals("PREMIUM")) {
            InputOutputUtils.getShared().writeFile(actionInputData.getActionId(),
                    "SearchRecommendation cannot be applied!");
            return;
        }

        List<MovieInputData> movies = InputOutputUtils.getShared().getInput().getMovies();
        List<SerialInputData> serials = InputOutputUtils.getShared().getInput().getSerials();


        RatingStore ratingStore = RatingStore.getInstance();
        // filmele nevazute de user dintr-un anumit gen
        List<String> unseen = new LinkedList<>();
        for (MovieInputData movieInputData : movies) {
            if (movieInputData.getGenres().contains(actionInputData.getGenre())
                    && !userInputData.getHistory().containsKey(movieInputData.getTitle())) {
                    unseen.add(movieInputData.getTitle());
            }
        }

        //serialele nevazute de user dintr-un anumit gen
        for (SerialInputData serialInputData : serials) {
            if (serialInputData.getGenres().contains(actionInputData.getGenre())
                    && !userInputData.getHistory().containsKey(serialInputData.getTitle())) {
                unseen.add(serialInputData.getTitle());
            }
        }

        if (unseen.isEmpty()) {
            InputOutputUtils.getShared().writeFile(actionInputData.getActionId(),
                    "SearchRecommendation cannot be applied!");
            return;
        }


       // Rating (key)          Setul de filme (value)
        Map<Double, Set<String>> ratingMap = new HashMap<>();
        for (String unseenVideo : unseen) {
            double rating = ratingStore.getRatingForTitle(unseenVideo);
            Set<String> moviesWithSameRating
                    = ratingMap.computeIfAbsent(rating, k -> new TreeSet<>());
            moviesWithSameRating.add(unseenVideo);
        }

        //se pune in lista finala sortata dupa rating
        List<String> results =
                ratingMap.entrySet().stream()
                        .sorted(Comparator.comparingDouble(Map.Entry::getKey))
                        .map(Map.Entry::getValue)
                        .flatMap(Set::stream)
                        .collect(Collectors.toList());

        String message = "SearchRecommendation result: [" + String.join(", ", results) + "]";

        InputOutputUtils.getShared().writeFile(actionInputData.getActionId(), message);
    }
}
