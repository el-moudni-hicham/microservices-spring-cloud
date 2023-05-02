package ma.enset.billingmicroservice.repository;

import ma.enset.billingmicroservice.entites.ProductItem;
import ma.enset.billingmicroservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
    public Collection<ProductItem> findByBillId(Long id);
}
