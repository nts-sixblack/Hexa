package nts.sixblack.hexa.service;

import nts.sixblack.hexa.form.PostsForm;
import nts.sixblack.hexa.form.StoryForm;
import nts.sixblack.hexa.model.PostsInfo;
import nts.sixblack.hexa.model.StoryInfo;
import nts.sixblack.hexa.model.UserStory;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StoryService {
    StoryInfo newPosts(StoryForm storyForm);

    List<UserStory> show(long userId, int page);

    List<StoryInfo> getListStoryOfUser(long userId);
}
