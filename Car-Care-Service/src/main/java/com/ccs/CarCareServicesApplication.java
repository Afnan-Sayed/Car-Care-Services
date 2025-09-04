package com.ccs;

import com.ccs.Models.User;
import com.ccs.Repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
public class CarCareServicesApplication
{

	public static void main(String[] args) {
		SpringApplication.run(CarCareServicesApplication.class, args);
	}

	@Bean
	CommandLineRunner initAdmins(UserRepo userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			// Admin 1
			if (userRepository.findByUsername("admin1").isEmpty()) {
				User admin1 = new User();
				admin1.setUsername("admin1");
				admin1.setPassword(passwordEncoder.encode("admin123"));
				admin1.setEmail("admin1@ccs.com");
				admin1.setPhone("01000000001");
				admin1.setRole(User.Role.ROLE_ADMIN);
				userRepository.save(admin1);
				System.out.println("Admin1 created successfully");
			}

			// Admin 2
			if (userRepository.findByUsername("admin2").isEmpty()) {
				User admin2 = new User();
				admin2.setUsername("admin2");
				admin2.setPassword(passwordEncoder.encode("admin456"));
				admin2.setEmail("admin2@ccs.com");
				admin2.setPhone("01000000002");
				admin2.setRole(User.Role.ROLE_ADMIN);
				userRepository.save(admin2);
				System.out.println("Admin2 created successfully");
			}
		};
	}
}