package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}