package Elevator;

import java.util.List;

public class Floor {

    int floorno;
    ExternalDispatcher dispatcher;

    public Floor(int f, List<ElevatorController> controllers){
        this.floorno = f;
//        InternalDispatcher internal = new InternalDispatcher(controllers);
        dispatcher = new ExternalDispatcher(controllers);

    }

    public void pressButton(){
        dispatcher.handleRequest(floorno);
    }
}
