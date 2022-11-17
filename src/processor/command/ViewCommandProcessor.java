package processor.command;

import fileio.ActionInputData;
import fileio.UserInputData;
import inputdata.InputOutputUtils;

public final class ViewCommandProcessor extends CommandProcessor {

  @Override
  public void processAction(final ActionInputData actionInputData) {
    String username = actionInputData.getUsername();
    String title = actionInputData.getTitle();

    UserInputData userInputData = getUserForUsername(username);

    // Am luat de cate ori am vazut un video si l-am incrementat cu 1
    int viewCount = userInputData.getHistory().getOrDefault(title, 0) + 1;
    userInputData.getHistory().put(title, viewCount);

    String message = String.format(
            "success -> %s was viewed with total views of %s", title, viewCount);

    InputOutputUtils.getShared().writeFile(actionInputData.getActionId(), message);


  }
}
