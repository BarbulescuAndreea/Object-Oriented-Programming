package processor.recommendation;

import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.UserInputData;
import inputdata.InputOutputUtils;

import java.util.List;

public final class StandardRecommandationProcessor extends RecommandationProcessor {
    @Override
    public void processAction(final ActionInputData actionInputData) {
        String username = actionInputData.getUsername();
        UserInputData userInputData = getUserForUsername(username);
        if (userInputData == null) {
            InputOutputUtils.getShared().writeFile(actionInputData.getActionId(),
                    "StandardRecommendation cannot be applied!");
            return;
        }

        List<MovieInputData> movies = InputOutputUtils.getShared().getInput().getMovies();

        for (MovieInputData movie : movies) {
            String movieTitle = movie.getTitle();
            if (!userInputData.getHistory().containsKey(movieTitle)) {
                String message = String.format("StandardRecommendation result: %s", movieTitle);
                InputOutputUtils.getShared().writeFile(actionInputData.getActionId(), message);
                return;
            }
        }
        InputOutputUtils.getShared().writeFile(actionInputData.getActionId(),
                "StandardRecommendation cannot be applied!");
    }
}
