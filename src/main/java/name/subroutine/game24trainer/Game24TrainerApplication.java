package name.subroutine.game24trainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

// Annotation shortcut for @Configuration, @EnableAutoConfiguration, and
// @ComponentScan:
@SpringBootApplication
@Import( Game24TrainerConfig.class )
public class Game24TrainerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Game24TrainerApplication.class, args);
	}
}
