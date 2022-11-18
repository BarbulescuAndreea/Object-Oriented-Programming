package router;

import fileio.ActionInputData;
import processor.query.ActorsQueryProcessor;
import processor.query.MoviesQueryProcessor;
import processor.query.ShowQueryProcessor;
import processor.query.UsersQueryProcessor;

public final class QueryActionRouter implements ActionRouter {

  @Override
  public void routeAction(final ActionInputData actionInputData) {
    if (actionInputData.getObjectType().equals("actors")) {
      ActorsQueryProcessor actorsQueryProcessor = new ActorsQueryProcessor();
      actorsQueryProcessor.processAction(actionInputData);
    } else if (actionInputData.getObjectType().equals("movies")) {
      MoviesQueryProcessor moviesQueryProcessor = new MoviesQueryProcessor();
      moviesQueryProcessor.processAction(actionInputData);
    } else if (actionInputData.getObjectType().equals("shows")) {
      ShowQueryProcessor showQueryProcessor = new ShowQueryProcessor();
      showQueryProcessor.processAction(actionInputData);
    } else {
      UsersQueryProcessor usersQueryProcessor = new UsersQueryProcessor();
      usersQueryProcessor.processAction(actionInputData);
    }
  }
}
