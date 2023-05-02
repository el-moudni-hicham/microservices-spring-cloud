package ma.enset.billingmicroservice;

import ma.enset.billingmicroservice.entites.Bill;
import ma.enset.billingmicroservice.entites.ProductItem;
import ma.enset.billingmicroservice.feign.CustomerRestClient;
import ma.enset.billingmicroservice.feign.ProductItemRestClient;
import ma.enset.billingmicroservice.models.Customer;
import ma.enset.billingmicroservice.models.Product;
import ma.enset.billingmicroservice.repository.BillRepository;
import ma.enset.billingmicroservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingMicroserviceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductItemRestClient productItemRestClient){
        return args -> {
            Customer customer = customerRestClient.findCustomerById(1L);
            Bill bill = billRepository.save(new Bill(null, new Date(), null, customer.getId(), null));
            PagedModel<Product> pageProducts = productItemRestClient.pageProducts();
            pageProducts.forEach(p -> {
                ProductItem productItem = new ProductItem();
                productItem.setPrice(p.getPrice());
                productItem.setQuantity(1 + new Random().nextInt(99));
                productItem.setBill(bill);
                productItem.setProductID(p.getId());
                productItemRepository.save(productItem);
            });
        };
    }
}
