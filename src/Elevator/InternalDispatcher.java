package Elevator;

import java.util.List;

public class InternalDispatcher {

    List<ElevatorController> controller;

    InternalDispatcher( List<ElevatorController> controller){
        this.controller = controller;
    }

    void handleRequest(int destination, Elevator e){
        for(ElevatorController e1: controller) {
            if (e.getID() == e1.elevator.getID()){
                e1.addRequest(destination);
            }
        }
    }
}
