package com.ebanking.payment;

import com.ebanking.payment.model.MyUser;
import com.ebanking.payment.model.UserBankAccount;
import com.ebanking.payment.repository.UserAccountRepo;
import com.ebanking.payment.service.MyUserService;
import com.ebanking.payment.util.LoggingUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

@SpringBootApplication
@EnableSwagger2
public class PaymentApplication {
	private static final Logger LOGGER = Logger.getLogger(PaymentApplication.class.getName());

	public static void main(String[] args) throws IOException {
		LoggingUtil.initLogManager();
		LOGGER.log(Level.INFO, "in main class");

		SpringApplication.run(PaymentApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(MyUserService myUserService, UserAccountRepo userAccountRepo) {
		return args -> {

			myUserService.saveUser(new MyUser(null, "John Travolta", "john", "1234"));
			myUserService.saveUser(new MyUser(null, "Will smith", "will", "1234"));
			myUserService.saveUser(new MyUser(null, "Jim Carry", "jim", "1234"));
			myUserService.saveUser(new MyUser(null, "Arnold Schwarzenegger", "arnold", "1234"));

			userAccountRepo.save(new UserBankAccount(null, "arnold", "GB54-BOFA-165050-12345678", 100_000.0));
		};
	}
}
