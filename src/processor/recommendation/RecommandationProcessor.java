package processor.recommendation;


import fileio.UserInputData;
import inputdata.InputOutputUtils;
import processor.ActionProcessor;

import java.util.List;

public abstract class RecommandationProcessor extends ActionProcessor {
    protected final UserInputData getUserForUsername(final String username) {
        List<UserInputData> users = InputOutputUtils.getShared().getInput().getUsers();
        for (UserInputData user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
