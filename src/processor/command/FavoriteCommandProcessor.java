package processor.command;

import fileio.ActionInputData;
import fileio.UserInputData;
import inputdata.InputOutputUtils;

public final class FavoriteCommandProcessor extends CommandProcessor {

  @Override
  public void processAction(final ActionInputData actionInputData) {
    String username = actionInputData.getUsername();
    String title = actionInputData.getTitle();

    UserInputData userInputData = getUserForUsername(username);
    if (userInputData == null) {
      return;
    }

    int viewCount = userInputData.getHistory().getOrDefault(title, 0);
    String message;

    if (viewCount == 0) {
      message = String.format("error -> %s is not seen", title);
    } else if (userInputData.getFavoriteMovies().contains(title)) {
      message = String.format("error -> %s is already in favourite list", title);
    } else {
      userInputData.getFavoriteMovies().add(title);
      message = String.format("success -> %s was added as favourite", title);
    }
    InputOutputUtils.getShared().writeFile(actionInputData.getActionId(), message);
  }
}
