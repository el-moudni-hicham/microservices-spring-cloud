package ma.enset.billingmicroservice.feign;

import ma.enset.billingmicroservice.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerRestClient {
    @GetMapping(path = "/customers/{id}")
    Customer findCustomerById(@PathVariable(name = "id")Long id);
}
