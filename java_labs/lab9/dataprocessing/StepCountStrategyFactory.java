package lab9.dataprocessing;

import lab9.storage.DataRepository;

public class StepCountStrategyFactory {
    public StepCountStrategy createStrategy(String strategyType, DataRepository dataRepository) {
        if (strategyType.equals("basic")) {
            return new BasicStepCountStrategy(dataRepository);
        }

        return new FilteredStepCountStrategy(dataRepository);
    }
}