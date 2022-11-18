package lab9.dataprocessing;

import lab9.storage.DataRepository;
import lab9.storage.SensorData;

import java.util.concurrent.TimeUnit;

public class FilteredStepCountStrategy implements StepCountStrategy {
    private static final int MAX_DIFF_STEPS_CONSECUTIVE_RECORDS = 1000;
    private static final long TIME_FOR_MAX_DIFF = TimeUnit.SECONDS.toMillis(1);

    private final DataRepository dataRepository;

    public FilteredStepCountStrategy(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public int getTotalSteps() {
        SensorData prevRecord = null;
        int sum = 0;
        for (SensorData sensorData : dataRepository.getSensorDataList()) {
            if (sensorData.getStepsCount() <= 0 || sensorData.getStepsCount() > MAX_DIFF_STEPS_CONSECUTIVE_RECORDS) {
                prevRecord = null;
                continue;
            }

            if (prevRecord == null) {
                sum += sensorData.getStepsCount();
            } else {
                    // timpul curent        -       timpul anterior
                if (sensorData.getTimestamp() - prevRecord.getTimestamp() > TIME_FOR_MAX_DIFF * 60) {
                    sum += sensorData.getStepsCount();
                }
            }

            prevRecord = sensorData;
        }

        return sum;
    }

    @Override
    public String getStrategyDescription() {
        return null;
    }
}
