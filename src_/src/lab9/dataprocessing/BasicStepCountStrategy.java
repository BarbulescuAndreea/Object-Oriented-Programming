package lab9.dataprocessing;

import lab9.storage.DataRepository;
import lab9.storage.SensorData;

public class BasicStepCountStrategy implements StepCountStrategy{
    private final DataRepository dataRepository;

    public BasicStepCountStrategy(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public int getTotalSteps() {
        return dataRepository.getSensorDataList().stream().map(SensorData::getStepsCount).mapToInt(i -> i).sum();
    }

    @Override
    public String getStrategyDescription() {
        return null;
    }
}
