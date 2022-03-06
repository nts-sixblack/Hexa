package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String userName, String password);

    List<User> findByEmail(String email);

    @Query("select u from User u where u.name like concat('%',:username,'%') ")
    List<User> findLikeName(@Param("username") String username);

    User findById(long userId);



}