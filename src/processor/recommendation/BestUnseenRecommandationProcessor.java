package processor.recommendation;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.UserInputData;
import inputdata.InputOutputUtils;
import store.RatingStore;

import java.util.LinkedList;
import java.util.List;

public final class BestUnseenRecommandationProcessor extends RecommandationProcessor {

    @Override
    public void processAction(final ActionInputData actionInputData) {
        String username = actionInputData.getUsername();
        String title = actionInputData.getTitle();

        RatingStore ratingStore = RatingStore.getInstance();
        UserInputData userInputData = getUserForUsername(username);
        if (userInputData == null) {
            String message = "BestRatedUnseenRecommendation cannot be applied!";
            InputOutputUtils.getShared().writeFile(actionInputData.getActionId(), message);

            return;
        }

        List<MovieInputData> movies = InputOutputUtils.getShared().getInput().getMovies();
        // 1 filtrez filmele nevazute de user
        List<String> unseenTitles = new LinkedList<>();
        for (MovieInputData movie : movies) {
            String movieTitle = movie.getTitle();
            if (!userInputData.getHistory().containsKey(movieTitle)) {
                unseenTitles.add(movieTitle);
            }
        }

        // 2 luam filmul cu cel mai mare rating
        String bestUnseenMovie = null;
        double bestScore = 0;
        for (String unseenTitle : unseenTitles) {
            double score = ratingStore.getRatingForTitle(unseenTitle);
            if (score > bestScore) {
                bestUnseenMovie = unseenTitle;
                bestScore = score;
            }
        }
        //luam unseenTitle de pe pozitia 0 pentru a
        //se respecta criterul cu ordinea de aparitie in baza
        //de date
        if (bestUnseenMovie == null && unseenTitles.size() > 0) {
            bestUnseenMovie = unseenTitles.get(0);
        }

        if (bestUnseenMovie == null) {
            String message = "BestRatedUnseenRecommendation cannot be applied!";
            InputOutputUtils.getShared().writeFile(actionInputData.getActionId(), message);
            return;
        }

        String message = String.format("BestRatedUnseenRecommendation result: %s", bestUnseenMovie);
        InputOutputUtils.getShared().writeFile(actionInputData.getActionId(), message);
    }
}
