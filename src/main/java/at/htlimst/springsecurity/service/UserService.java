package at.htlimst.springsecurity.service;

import at.htlimst.springsecurity.dto.UserRegistrationDto;
import at.htlimst.springsecurity.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    User save(UserRegistrationDto userRegistrationDto);

}
