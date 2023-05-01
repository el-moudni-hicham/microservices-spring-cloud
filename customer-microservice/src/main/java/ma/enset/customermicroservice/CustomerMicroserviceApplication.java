package ma.enset.customermicroservice;

import ma.enset.customermicroservice.entites.Customer;
import ma.enset.customermicroservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerMicroserviceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository){
		return args -> {
			customerRepository.save(new Customer(null,"Hicham","him@gmail.com"));
			customerRepository.save(new Customer(null,"Hafid","haf@gmail.com"));
			customerRepository.save(new Customer(null,"Mahfoud","mah@gmail.com"));
			customerRepository.findAll().forEach(c -> {
				System.out.println(c.getName());
			});
		};
	}

}
