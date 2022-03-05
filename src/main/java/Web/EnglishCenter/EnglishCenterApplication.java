package Web.EnglishCenter;

import Web.EnglishCenter.entity.user.Authentication;
import Web.EnglishCenter.entity.user.Users;
import Web.EnglishCenter.service.AuthenticationService;
import Web.EnglishCenter.service.UsersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EnglishCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnglishCenterApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
