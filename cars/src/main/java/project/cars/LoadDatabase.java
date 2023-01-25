package project.cars;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CarRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Car("AB-001-XY", "Renault", 14_000, false)));
            log.info("Preloading " + repository.save(new Car("CD-002-MN", "Peugeot", 10_000, false)));
            log.info("Preloading " + repository.save(new Car("EF-003-XY", "Citroen", 12_000, false)));
            log.info("Preloading " + repository.save(new Car("GH-004-VW", "Volkswagen", 20_000, false)));
        };
    }

}
