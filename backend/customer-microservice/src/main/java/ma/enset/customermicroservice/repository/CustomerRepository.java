package ma.enset.customermicroservice.repository;

import ma.enset.customermicroservice.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
