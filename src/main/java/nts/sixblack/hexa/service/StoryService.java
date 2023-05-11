package nts.sixblack.hexa.service;

import nts.sixblack.hexa.form.PostsForm;
import nts.sixblack.hexa.form.StoryForm;
import nts.sixblack.hexa.model.PostsInfo;
import nts.sixblack.hexa.model.StoryInfo;
import org.springframework.stereotype.Service;

public interface StoryService {
    StoryInfo newPosts(StoryForm storyForm);
}
