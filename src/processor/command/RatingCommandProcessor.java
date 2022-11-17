package processor.command;

import fileio.ActionInputData;
import fileio.UserInputData;
import inputdata.InputOutputUtils;
import store.RatingException;
import store.RatingStore;

public final class RatingCommandProcessor extends CommandProcessor {

  @Override
  public void processAction(final ActionInputData actionInputData) {
    RatingStore ratingStore = RatingStore.getInstance();
    String title = actionInputData.getTitle();
    String username = actionInputData.getUsername();
    double rating = actionInputData.getGrade();

    UserInputData userInputData = getUserForUsername(username);
    if (userInputData == null) {
      return;
    }

    if (!userInputData.getHistory().containsKey(title)) {
      String message = String.format("error -> %s is not seen", title);
      InputOutputUtils.getShared().writeFile(actionInputData.getActionId(), message);
      return;
    }

    try {
      int seasonNumber = actionInputData.getSeasonNumber();
      if (seasonNumber == 0) { //Verificam daca dam rating de show sau film
        // Film
        ratingStore.addRating(title, username, rating);
      } else {
        // SHOW
        ratingStore.addRating(title, seasonNumber, username, rating);

      }

      //se scrie mesajul corespunzator la output
      // mesajul de eroare este daca un user a dat deja rating
      String message = String.format("success -> %s was rated with %s by %s",
          title, rating, username);
      InputOutputUtils.getShared().writeFile(actionInputData.getActionId(), message);
    } catch (RatingException e) {
      // Scriere mesaj eroare
      String message = String.format("error -> %s has been already rated", title);
      InputOutputUtils.getShared().writeFile(actionInputData.getActionId(), message);
    }
  }
}
