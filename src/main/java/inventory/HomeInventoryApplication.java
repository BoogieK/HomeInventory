package inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import inventory.data.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class HomeInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeInventoryApplication.class, args);
    }

}
