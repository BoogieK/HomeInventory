package inventory.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import inventory.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
