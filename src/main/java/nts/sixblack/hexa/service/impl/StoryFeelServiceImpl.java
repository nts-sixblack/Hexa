package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.*;
import nts.sixblack.hexa.form.Like;
import nts.sixblack.hexa.repository.StoryFeelRepository;
import nts.sixblack.hexa.service.StoryFeelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoryFeelServiceImpl implements StoryFeelService {

    @Autowired
    private StoryFeelRepository storyFeelRepository;

    @Override
    public void like(Like like) {
        long postsFeelId = checkFeel(like.getTusId(),like.getUserId());
        if (postsFeelId > 0){
            delete(postsFeelId);
        } else {
            User user = new User();
            user.setUserId(like.getUserId());
            Story story = new Story();
            story.setStoryId(like.getTusId());

            StoryFeel storyFeel = new StoryFeel();
            storyFeel.setFeel(true);
            storyFeel.setUser(user);
            storyFeel.setStory(story);

            save(storyFeel);
        }
    }

    @Override
    public long checkFeel(long storyId, long userId) {
        StoryFeel storyFeel = storyFeelRepository.checkFeelStoryUser(storyId, userId);
        if (storyFeel!=null) {
            return storyFeel.getStoryFeelId();
        }
        return  0;
    }

    @Override
    public void delete(long storyId) {
        storyFeelRepository.deleteById(storyId);
    }

    @Override
    public StoryFeel save(StoryFeel storyFeel) {
        return storyFeelRepository.save(storyFeel);
    }
}
