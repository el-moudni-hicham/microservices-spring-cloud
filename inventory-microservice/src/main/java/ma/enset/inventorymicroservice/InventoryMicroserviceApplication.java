package ma.enset.inventorymicroservice;

import ma.enset.inventorymicroservice.entites.Product;
import ma.enset.inventorymicroservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryMicroserviceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository){
        return args -> {
            productRepository.save(new Product(null, "XML Book",355.25,37));
            productRepository.save(new Product(null, "Angular Book",467.33,45));
            productRepository.save(new Product(null, "Spring Book",549.00,89));
            productRepository.findAll().forEach(p -> {
                System.out.println(p.getName());
            });
        };
    }
}

