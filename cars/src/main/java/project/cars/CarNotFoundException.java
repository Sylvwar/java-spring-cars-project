package project.cars;

public class CarNotFoundException extends RuntimeException {
    CarNotFoundException(Integer id) {
        super("Car " + id + " Not Found");
    }

    CarNotFoundException(String plate) {
        super("Car with license plate " + plate + " Not Found");
    }
}
