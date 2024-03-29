package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Follow;
import nts.sixblack.hexa.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Follow findByUserSenderAndUserRecipient(User userSender, User userRecipient);

    @Query("select f from Follow f where f.userRecipient.userId = ?1 and f.status=false")
    List<Follow> requestListFollow(long userRecipient);

    @Query("select f from Follow f where f.userRecipient.userId = ?1 and f.status=false")
    List<Follow> requestListFollowNumber(long userRecipient, Pageable pageable);

    List<Follow> findByUserRecipientAndStatus(User user, boolean status);

    List<Follow> findByUserRecipientAndStatus(User user, boolean status, Pageable pageable);

    List<Follow> findByUserSenderAndStatus(User user, boolean status);

    List<Follow> findByUserSenderAndStatus(User user, boolean status, Pageable pageable);

    List<Follow> findByUserRecipient(User user);

    Follow findByFollowId(long followId);

    int countByUserRecipientAndStatus(User user, boolean status);

    int countByUserSenderAndStatus(User user, boolean status);
}