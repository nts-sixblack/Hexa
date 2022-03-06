package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Follow;
import nts.sixblack.hexa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    public Follow findByUserSenderAndUserRecipient(User userSender, User userRecipient);

    @Query("select f.followId from Follow f where f.userRecipient.userId = ?1 and f.status=false")
    public List<Long> requestListFollow(long userRecipient);

    public Follow findByFollowId(long followId);

    List<Follow> findByUserRecipient(User user);
}