package project.cars;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    private final CarRepository repository;

    public LocationController(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cars")
    public List<Car> getCars() {
        return repository.findAll();
    }

    @GetMapping("/cars/{id}")
    public Car getCar(@PathVariable Integer id) throws CarNotFoundException {
        return repository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
    }

    @PutMapping("cars/rent/{plate}")
    public Car rentCar(@PathVariable String plate, @RequestParam boolean rent) throws CarNotFoundException {
        return repository.findByPlate(plate)
                .map(car -> {
                    car.setRented(rent);
                    return repository.save(car);
                })
                .orElseThrow(() -> new CarNotFoundException(plate));
    }

    @PostMapping("/cars")
    public Car addCar(@RequestBody Car car) {
        return repository.save(car);
    }

    @PutMapping("/cars/{id}")
    public Car updateCar(@PathVariable Integer id, @RequestBody Car newCar) throws CarNotFoundException {
        return repository.findById(id)
                .map(car -> {
                    car.setPlate(newCar.getPlate());
                    car.setPrice(newCar.getPrice());
                    return repository.save(car);
                })
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable Integer id) {
        repository.deleteById(id);
    }

}
