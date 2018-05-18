package by.bsuir.road.model;

/**
 *  The class of the car that runs in the new thread.
 */
public class Car extends Thread {
    /** Default sleep time. **/
    private static final int DEFAULT_SLEEP_TIME = 100;
    /** The minimum speed. **/
    private static final int MINIMUM_SPEED = 50;
    /** The difference in speed.
     *  Added 1, because this const use in random func.
     **/
    private static final int DIFFERENCE_IN_SPEED = 21;

    /**
     *  Current speed of the car.
     */
    private int speed;

    /**
     *  Default constructor that initialize speed.
     */
    public Car() {
        changeSpeed();
    }

    @Override
    public void run() {
        do {
            if (!Thread.interrupted()) {
                changeSpeed();
            } else {
                return;
            }

            try {
                Thread.sleep(DEFAULT_SLEEP_TIME);
            } catch (InterruptedException e) {
                return;
            }

        } while (true);
    }

    /**
     *  @return Current speed of the car.
     */
    public synchronized int getSpeed() {
        return speed;
    }

    /**
     *  Changes the speed of the car in the range from 50 to 70.
     */
    private synchronized void changeSpeed() {
        speed = (int) (MINIMUM_SPEED + Math.random() * DIFFERENCE_IN_SPEED);
    }
}
