package inventory.data;

import org.springframework.data.jpa.repository.JpaRepository;

import inventory.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
