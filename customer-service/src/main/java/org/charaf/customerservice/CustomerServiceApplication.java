package org.charaf.cutomerservice;

import org.charaf.customerservice.config.GlobalConfig;
import org.charaf.cutomerservice.entities.Customer;
import org.charaf.cutomerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

@Bean
	public CommandLineRunner CustomerServiceApplication(CustomerRepository customerRepository) {
	return args -> {
		List<Customer> customers = List.of(
				Customer.builder()
						.firstName("charaf")
						.lastName("benichou")
						.email("charaf@gmail.com")
						.build(),
				Customer.builder()
						.firstName("samir")
						.lastName("omari")
						.email("samir@gmail.com")
						.build()
		);

		customerRepository.saveAll(customers);
	};

}

}
