package router;

import fileio.ActionInputData;
import inputdata.InputOutputUtils;
import java.util.Map;

import static common.Constants.QUERY;
import static common.Constants.COMMAND;
import static common.Constants.RECOMMENDATION;

public final class GeneralRouter implements ActionRouter {
  private static final Map<String, ActionRouter> ROUTERMAP = Map.of(
      QUERY, new QueryActionRouter(),
      COMMAND, new CommandActionRouter(),
      RECOMMENDATION, new RecommendationActionRouter()
  );

  /**
   * efectueaza actiunea data(query, command, recommandation) daca aceasta nu este nula.
   * @param actionInputData
   */
  @Override
  public void routeAction(final ActionInputData actionInputData) {
    ActionRouter actionRouter = ROUTERMAP.get(actionInputData.getActionType());
    if (actionRouter != null) {
      actionRouter.routeAction(actionInputData);
    }
  }

  /**
   * preia actiunea care trebuie efectuala
   */
  public void routeInput() {
    for (ActionInputData actionInputData : InputOutputUtils.getShared().getInput().getCommands()) {
      routeAction(actionInputData);
    }
  }
}
