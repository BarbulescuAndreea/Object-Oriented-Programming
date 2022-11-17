package router;

import fileio.ActionInputData;

public interface ActionRouter {
  /**
   * interfata comuna de actiune
   * @param actionInputData
   */
  void routeAction(ActionInputData actionInputData);
}
