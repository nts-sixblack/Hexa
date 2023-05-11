package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.config.TimeConfig;
import nts.sixblack.hexa.entity.*;
import nts.sixblack.hexa.form.StoryForm;
import nts.sixblack.hexa.model.PostsImageInfo;
import nts.sixblack.hexa.model.PostsInfo;
import nts.sixblack.hexa.model.StoryInfo;
import nts.sixblack.hexa.model.UserInfo;
import nts.sixblack.hexa.repository.StoryRepository;
import nts.sixblack.hexa.service.CloudinaryService;
import nts.sixblack.hexa.service.StoryService;
import nts.sixblack.hexa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private UserService userService;

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private CloudinaryService cloudinaryService;
    @Override
    public StoryInfo newPosts(StoryForm storyForm) {

        System.out.println(storyForm.toString());
        User user = new User();
        user.setUserId(storyForm.getUserId());

        Story story = new Story();
        story.setType(storyForm.getType());
        story.setLink(cloudinaryService.uploadFile(storyForm.getFiles())+"");
        story.setDateCreate(new Date());
        story.setUser(user);

        Story s = storyRepository.save(story);

        StoryInfo storyInfo = new StoryInfo();
        storyInfo.setStoryId(s.getStoryId());
        storyInfo.setLink(story.getLink());
        storyInfo.setType(story.getType());
        storyInfo.setDateCreate(TimeConfig.getTime(story.getDateCreate()));

        return storyInfo;
    }
}
