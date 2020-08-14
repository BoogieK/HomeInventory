package inventory.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import inventory.User;
import inventory.security.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}