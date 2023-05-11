package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Story;
import nts.sixblack.hexa.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {
//    @Query("select s.storyId " +
//            "from Story s join s.user su " +
//            "where su in (select f.userRecipient from Follow f where f.userSender = ?1 and f.status = TRUE )" +
//            "order by s.dateCreate desc ")
//    List<Long> listNumberPostShow(User userSender, Pageable pageable);

    @Query("select s.storyId from Story s where s.userId = ?1")
    List<Long> listStoryByUser(long userId);

    Story findByStoryId(long storyId);
}