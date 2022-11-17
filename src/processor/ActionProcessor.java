package processor;

import fileio.ActionInputData;

public abstract class ActionProcessor {
  /**
   * clasa abstracta comuna pentru procesarea actiunii
   * @param actionInputData
   */
  public abstract void processAction(ActionInputData actionInputData);
}
