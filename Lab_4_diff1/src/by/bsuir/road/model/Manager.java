package by.bsuir.road.model;

import java.util.LinkedList;
import java.util.List;

/**
 * A class that creates and manages cars.
 */
public class Manager {
    /**
     * Limit of the speed.
     */
    private static final int SPEED_LIMIT = 60;

    /**
     * List of cars.
     */
    private List<Car> cars = new LinkedList<>();

    /**
     * Default constructor.
     */
    public Manager() {
    }

    /**
     * Creates a new car and launches thread.
     */
    public void addCar() {
        var car = new Car();
        cars.add(car);
        car.start();
    }

    /**
     * @return String with information about violations.
     */
    public String getViolations() {
        int carSpeed;
        int maxSpeed = 0;
        int countOfViolations = 0;
        for (Car car
                : cars) {
            carSpeed = car.getSpeed();
            if (SPEED_LIMIT < carSpeed) {
                car.interrupt();
                try {
                    car.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (maxSpeed < carSpeed) {
                    maxSpeed = carSpeed;
                }
                ++countOfViolations;
            }
        }

        return "Count of violations: " + countOfViolations
                + "\nMax speed: " + maxSpeed;
    }

    /**
     * Closes all threads of cars.
     */
    public void removeAll() {
        for (Car car
                : cars) {
            if (car.isAlive()) {
                car.interrupt();
                try {
                    car.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        cars.clear();
    }
}
