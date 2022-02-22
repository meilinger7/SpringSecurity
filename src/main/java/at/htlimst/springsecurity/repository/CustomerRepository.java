package at.htlimst.springsecurity.repository;

import at.htlimst.springsecurity.model.Customer;
import at.htlimst.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);


}
