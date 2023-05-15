package ma.enset.billingmicroservice.repository;

import ma.enset.billingmicroservice.entites.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findBillsByCustmerID(Long id);
}
