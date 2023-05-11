package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.config.TimeConfig;
import nts.sixblack.hexa.entity.*;
import nts.sixblack.hexa.form.StoryForm;
import nts.sixblack.hexa.model.*;
import nts.sixblack.hexa.repository.StoryRepository;
import nts.sixblack.hexa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private FollowService followService;

    @Autowired
    private StoryFeelService storyFeelService;

    @Autowired
    private StoryCommentService storyCommentService;

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
        story.setUserId(storyForm.getUserId());

        Story s = storyRepository.save(story);

        StoryInfo storyInfo = new StoryInfo();
        storyInfo.setStoryId(s.getStoryId());
        storyInfo.setLink(story.getLink());
        storyInfo.setType(story.getType());
        storyInfo.setDateCreate(TimeConfig.getTime(story.getDateCreate()));

        return storyInfo;
    }

    @Override
    public List<UserStory> show(long userId, int page) {
        List<UserStory> userStoryList = new ArrayList<UserStory>();
        User user = new User();
        user.setUserId(userId);
        Pageable pageable = PageRequest.of(page, 10);

//        List<Long> storyList = storyRepository.listNumberPostShow(user, pageable);
        List<FollowInfo> followInfoList = followService.followerList(userId);

        for (FollowInfo followInfo: followInfoList) {
            UserStory userStory = new UserStory();
            userStory.setUserId(followInfo.getUserId());
            userStory.setName(followInfo.getUserName());
            userStory.setImage(followInfo.getUserImage());
            userStory.setStoryInfoList(getListStoryOfUser(followInfo.getUserId()));

            userStoryList.add(userStory);
        }

        return userStoryList;
    }

    @Override
    public List<StoryInfo> getListStoryOfUser(long userId) {
        List<StoryInfo> storyInfoList = new ArrayList<StoryInfo>();

        List<Long> stories = storyRepository.listStoryByUser(userId);

        for (long storyId: stories) {
            Story story = storyRepository.findByStoryId(storyId);
            StoryInfo storyInfo = new StoryInfo();

            storyInfo.setStoryId(story.getStoryId());
            storyInfo.setLink(story.getLink());
            storyInfo.setType(story.getType());
            storyInfo.setDateCreate(TimeConfig.getTime(story.getDateCreate()));
            storyInfo.setTotalFeel(storyFeelService.getTotalFeel(story.getStoryId()));
            storyInfo.setTotalComment(storyCommentService.getTotalComment(story.getStoryId()));

            storyInfoList.add(storyInfo);
        }

        return storyInfoList;
    }
}
