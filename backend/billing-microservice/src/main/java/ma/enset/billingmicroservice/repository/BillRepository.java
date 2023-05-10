package ma.enset.billingmicroservice.repository;

import ma.enset.billingmicroservice.entites.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
