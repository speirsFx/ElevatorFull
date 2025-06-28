package Elevator;
import java.util.List;

public class ExternalDispatcher {


    private final List<ElevatorController> controllers;

    public ExternalDispatcher(List<ElevatorController> controllers) {
        this.controllers = controllers;
    }

    public void handleRequest(int destination) {
        ElevatorController best = null;
        int minDistance = Integer.MAX_VALUE;

        for (ElevatorController controller : controllers) {
            int dist = Math.abs(controller.elevator.currentFloor - destination);
            if (dist < minDistance) {
                minDistance = dist;
                best = controller;
            }
        }

        if (best != null) {
            best.addRequest(destination);
            System.out.println("ExternalDispatcher: Assigned floor "
                    + destination + " to elevator " + best.elevator.getID());
        }
    }


}
