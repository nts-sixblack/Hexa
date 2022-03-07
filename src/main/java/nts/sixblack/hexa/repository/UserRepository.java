package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u.followStatus from User u where u.userId = ?1")
    boolean checkFollowStatus(long userId);

    User findByUserId(long userId);

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

}