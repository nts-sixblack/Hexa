package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Contact;
import nts.sixblack.hexa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("select c from Contact c where (c.user1.userId = ?1 and c.user2.userId = ?2) or (c.user1.userId = ?2 and c.user2.userId = ?1)")
    Contact findChannelIdByUser(long userId1, long userId2);


}