import Elevator.*;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<ElevatorController> controllers = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            Elevator e = new Elevator(i);
            ElevatorController e1 = new ElevatorController(e);
            controllers.add(e1);
            Thread t = new Thread(e1);
            t.start();
            threads.add(t);
        }

        ExternalDispatcher external = new ExternalDispatcher(controllers);
        Floor f = new Floor(3,controllers);
        f.pressButton();
        // Let elevators finish
        Thread.sleep(1000);
        for (ElevatorController c : controllers) c.stop();

    }
}