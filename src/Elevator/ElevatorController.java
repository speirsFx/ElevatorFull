package Elevator;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import Enum.*;

public class ElevatorController implements Runnable{

    Elevator elevator;
    private final BlockingQueue<Integer> requestQueue = new LinkedBlockingQueue<>();
    private volatile boolean running = true;
    // MinHeap: ascending floor order
    private final PriorityQueue<Integer> upQueue = new PriorityQueue<>();
    // MaxHeap: descending floor order
    private final PriorityQueue<Integer> downQueue = new PriorityQueue<>(Collections.reverseOrder());


    public ElevatorController(Elevator e){
        this.elevator = e;
    }

    void moveCar(int destination) throws InterruptedException {
        elevator.openDoor();
        elevator.closeDoor();
        elevator.move(destination);
        elevator.openDoor();
        elevator.closeDoor();
    }

    void addRequest(int floor){
        synchronized (this) {
            int currFloor = elevator.currentFloor;
            if (floor > currFloor) {
                upQueue.offer(floor);
            } else if (floor < currFloor) {
                downQueue.offer(floor);
            } else {
                // If same floor, treat as immediate stop
                upQueue.offer(floor); // arbitrary
            }
        }
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
//                Integer targetFloor = requestQueue.take();
                processRequests();
                Thread.sleep(100); // avoid tight loop
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    private void processRequests() throws InterruptedException {
        if (elevator.dir == Direction.IDLE) {
            if (!upQueue.isEmpty()) elevator.dir = Direction.UP;
            else if (!downQueue.isEmpty()) elevator.dir = Direction.DOWN;
        }

        if (elevator.dir == Direction.UP) {
            while (!upQueue.isEmpty()) {
                int target = upQueue.poll();
                elevator.move(target);
            }
            if (!downQueue.isEmpty()) {
                elevator.dir = Direction.DOWN;
            } else {
                elevator.dir = Direction.IDLE;
            }
        }

        if (elevator.dir == Direction.DOWN) {
            while (!downQueue.isEmpty()) {
                int target = downQueue.poll();
                elevator.move(target);
            }
            if (!upQueue.isEmpty()) {
                elevator.dir = Direction.UP;
            } else {
                elevator.dir = Direction.IDLE;
            }
        }
    }
}
