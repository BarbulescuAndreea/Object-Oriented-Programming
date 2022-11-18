package lab9.communication;

import lab9.communication.ServerMessage;
import lab9.storage.SensorData;

import java.util.Observable;
import java.util.Observer;

public class ServerCommunicationController implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        // pasul 1
        SensorData sensorData = (SensorData) arg;
        // pasul 2 - fac un serverMessage
        ServerMessage serverMessage = new ServerMessage(
                sensorData.getStepsCount(),
                100,
                sensorData.getTimestamp()
                );
        // pasul 3 afisare
        System.out.println(serverMessage);
    }
}
