package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.PostsFeel;
import nts.sixblack.hexa.entity.Story;
import nts.sixblack.hexa.entity.StoryFeel;
import nts.sixblack.hexa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoryFeelRepository extends JpaRepository<StoryFeel, Long> {
    @Query("select s from StoryFeel s where s.story.storyId = ?1 and s.user.userId = ?2")
    StoryFeel checkFeelStoryUser(long storyId, long userId);

    long countAllByStory(Story story);
}