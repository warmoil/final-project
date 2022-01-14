package joy.world.fc.finalproject.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("joy.world.fc.finalproject.core")
@EnableJpaRepositories("joy.world.fc.finalproject.core")
@SpringBootApplication(scanBasePackages = "joy.world.fc.finalproject")
@EnableJpaAuditing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
