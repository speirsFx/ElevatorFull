package Elevator;
import Enum.*;



public class Elevator {

    private final int id;
    int currentFloor;
    Direction dir;
    Door door;


    public Elevator(int id) {
        this.id = id;
        currentFloor = 0;
        dir = Direction.IDLE;
        door = Door.CLOSED;
    }

    void move(int destination) throws InterruptedException {
        if (destination - currentFloor > 0) {
            this.dir = Direction.UP;
            for (int i = currentFloor + 1; i <= destination; i++) {
                Thread.sleep(1000);
                System.out.println("Moving up " + "Passing floor " + i);
            }
        } else if (destination - currentFloor < 0) {
            this.dir = Direction.DOWN;
            for (int i = currentFloor - 1; i >= destination; i--) {
                Thread.sleep(1000);
                System.out.println("Moving DOWN " + "Passing floor " + i);
            }
        } else {
            System.out.println("Already there");
        }

        this.currentFloor = destination;
        this.dir = Direction.IDLE;
    }

    int getID() {
        return this.id;
    }

    void openDoor() throws InterruptedException {
        if (door == Door.CLOSED) {
            Thread.sleep(1000);
            System.out.println("Door opened");
            door = Door.OPEN;
        }

    }

    void closeDoor() throws InterruptedException {
        if (door == Door.OPEN) {
            Thread.sleep(1000);
            System.out.println("Door opened");
            door = Door.CLOSED;
        }

    }
}

