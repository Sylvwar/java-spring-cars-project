package project.users.test;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import project.users.user.User;
import project.users.user.UserRepository;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final UserRepository repository;

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test from secured endpoint");
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return repository.findAll();
    }

    @GetMapping("/cars")
    public Car searchCar() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/cars";
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(header);
        ResponseEntity<Car[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, Car[].class);
        List<Car> cars = Arrays.asList(responseEntity.getBody());
        Car car = null;
        if (cars.size() > 0) {
            car = cars.get(0);
            String plate = car.getPlate();
            url = "http://localhost:8080/cars/" + plate;
            ResponseEntity<Car> entityCar = restTemplate.getForEntity(url, Car.class);
            car = entityCar.getBody();
        }
        return car;
    }

}
