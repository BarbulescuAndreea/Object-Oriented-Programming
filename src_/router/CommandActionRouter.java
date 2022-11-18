package router;

import fileio.ActionInputData;
import processor.command.FavoriteCommandProcessor;
import processor.command.RatingCommandProcessor;
import processor.command.ViewCommandProcessor;

public final class CommandActionRouter implements ActionRouter {

  @Override
  public void routeAction(final ActionInputData actionInputData) {
    if (actionInputData.getType().equals("favorite")) {
      FavoriteCommandProcessor favoriteCommandProcessor = new FavoriteCommandProcessor();
      favoriteCommandProcessor.processAction(actionInputData);
    } else if (actionInputData.getType().equals("view")) {
      ViewCommandProcessor viewCommandProcessor = new ViewCommandProcessor();
      viewCommandProcessor.processAction(actionInputData);
    } else if (actionInputData.getType().equals("rating")) {
      RatingCommandProcessor ratingCommandProcessor = new RatingCommandProcessor();
      ratingCommandProcessor.processAction(actionInputData);
    }
  }
}
