package project.users.test;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;;

@Entity
public class Car {

    private @Id @GeneratedValue(strategy = GenerationType.AUTO) int id;
    private String plate;
    private String brand;
    private int price;
    private boolean rented;

    public Car() {
    }

    public Car(String plate, String brand, int price, boolean rented) {
        this.plate = plate;
        this.brand = brand;
        this.price = price;
        this.rented = rented;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Car))
            return false;
        Car other = (Car) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Car [id=" + id + ", plate=" + plate + ", brand=" + brand + ", price=" + price + ", rented=" + rented
                + "]";
    }

}
