package ma.enset.billingmicroservice.web;

import ma.enset.billingmicroservice.entites.Bill;
import ma.enset.billingmicroservice.feign.CustomerRestClient;
import ma.enset.billingmicroservice.feign.ProductItemRestClient;
import ma.enset.billingmicroservice.models.Customer;
import ma.enset.billingmicroservice.models.Product;
import ma.enset.billingmicroservice.repository.BillRepository;
import ma.enset.billingmicroservice.repository.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BillingRestController {
    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClient customerRestClient;
    private ProductItemRestClient productItemRestClient;

    public BillingRestController(BillRepository billRepository,
                                 ProductItemRepository productItemRepository,
                                 CustomerRestClient customerRestClient,
                                 ProductItemRestClient productItemRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRestClient = productItemRestClient;
    }

    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id){
        Bill bill = billRepository.findById(id).get();
        Customer customer = customerRestClient.findCustomerById(bill.getCustmerID());
        bill.setCustomer(customer);

        bill.getProductItems().forEach(pi -> {
            Product product = productItemRestClient.findProductById(pi.getProductID());
            //pi.setProduct(product);
            pi.setProductName(product.getName());
        });
        return bill;
    }

    @GetMapping(path = "/customerBills/{id}")
    public List<Bill> getCustomerBills(@PathVariable(name = "id") Long id){
        List<Bill> bills = billRepository.findBillsByCustmerID(id);
        bills.forEach(bill -> {
            Customer customer = customerRestClient.findCustomerById(bill.getCustmerID());
            bill.setCustomer(customer);

            bill.getProductItems().forEach(pi -> {
                Product product = productItemRestClient.findProductById(pi.getProductID());
                //pi.setProduct(product);
                pi.setProductName(product.getName());
            });
        });

        return bills;
    }
}
