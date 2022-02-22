package at.htlimst.springsecurity;

import at.htlimst.springsecurity.model.Customer;
import at.htlimst.springsecurity.model.User;
import at.htlimst.springsecurity.repository.CustomerRepository;
import at.htlimst.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DbInit(UserRepository userRepository, CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Delete all
        this.customerRepository.deleteAll();

        // Crete users
        Customer kunde = new Customer("dan", "hdf", "kunde@gmail.com", passwordEncoder.encode("kunde"));


        // Save to db
        this.customerRepository.save(kunde);
    }
}