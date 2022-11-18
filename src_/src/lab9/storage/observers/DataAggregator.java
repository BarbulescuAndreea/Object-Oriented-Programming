package lab9.storage.observers;

import lab9.dataprocessing.StepCountStrategy;

import java.util.Observable;
import java.util.Observer;

public class DataAggregator implements Observer {
    private final StepCountStrategy strategy;

    public DataAggregator(StepCountStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void update(Observable o, Object arg) {
        int totalSteps = strategy.getTotalSteps();
        System.out.println("Total steps:" + totalSteps);
    }
}
