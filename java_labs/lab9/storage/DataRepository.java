package lab9.storage;


import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 * Persists sensor data. Observable, its observers are notified when data is added it to.
 */
public class DataRepository extends Observable { // TODO make this an Observable
    private final List<SensorData> sensorDataList;

    public DataRepository() {
        this.sensorDataList = new LinkedList<>();
    }

    public void addData(SensorData dataRecord){
        // TODO
        // store data (e.g. in a List)
        sensorDataList.add(dataRecord);
        // notifiy observers
        this.setChanged();
        this.notifyObservers(dataRecord);
    }

    // TODO implement a method to get the stored data (needed by the strategies)
    public List<SensorData> getSensorDataList() {
        return sensorDataList;
    }
}


