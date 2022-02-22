package at.htlimst.springsecurity.service.SecurityService;

import at.htlimst.springsecurity.dto.UserPojo;
import at.htlimst.springsecurity.model.Customer;
import at.htlimst.springsecurity.model.Role;
import at.htlimst.springsecurity.model.User;
import at.htlimst.springsecurity.repository.CustomerRepository;
import at.htlimst.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Observable;
import java.util.Optional;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public UserPrincipalDetailsService(UserRepository userRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPojo user = findUserByEmail(username);

        if (user ==null) {
            throw new UsernameNotFoundException("User not found");
        }

        UserPrincipal userPrincipal = new UserPrincipal(user);

        return userPrincipal;
    }


    public UserPojo findUserByEmail(String email){
        UserPojo userPojo = null;
        Optional<User> userOptional = Optional.ofNullable(this.userRepository.findByEmail(email));
        Optional<Customer> customerOptional = Optional.ofNullable(this.customerRepository.findByEmail(email));

        if (userOptional.isPresent()){
            userPojo = UserPojo.builder().
                    username(userOptional.get().getEmail()).
                    password(userOptional.get().getPassword()).
                    roles(userOptional.get().getRoles()).
                    build();
        } else if (customerOptional.isPresent()){
            userPojo = UserPojo.builder().
                    username(customerOptional.get().getEmail()).
                    password(customerOptional.get().getPassword()).
                    roles(Arrays.asList(new Role("ROLE_USER"))).
                    build();
        }

        return userPojo;
    }
}
