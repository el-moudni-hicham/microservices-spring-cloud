package ma.enset.billingmicroservice.feign;

import ma.enset.billingmicroservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service")
public interface ProductItemRestClient {
    @GetMapping(path = "/products/{id}")
    Product findProductById(@PathVariable(name = "id") Long id);

    @GetMapping(path = "/products")
    PagedModel<Product> pageProducts(); //@RequestParam(value = "page") int page, @RequestParam(value = "size") int size
}
