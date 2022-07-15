package com.ebanking.payment;

import com.ebanking.payment.util.LoggingUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.util.logging.*;

@SpringBootApplication
@EnableSwagger2
public class PaymentApplication {
	private  static final Logger LOGGER = Logger.getLogger(PaymentApplication.class.getName());



	public static void main(String[] args) throws IOException {
		LoggingUtil.initLogManager();
		LOGGER.log(Level.INFO, "in main class");

		SpringApplication.run(PaymentApplication.class, args);
	}

}
