package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.*;
import nts.sixblack.hexa.form.CommentForm;
import nts.sixblack.hexa.repository.StoryCommentRepository;
import nts.sixblack.hexa.service.StoryCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StoryCommentServiceImpl implements StoryCommentService {

    @Autowired
    private StoryCommentRepository storyCommentRepository;

    @Override
    public void comment(CommentForm commentForm) {
        User user = new User();
        user.setUserId(commentForm.getUserId());
        Story story = new Story();
        story.setStoryId(commentForm.getTusId());

        StoryComment storyComment = new StoryComment();
        storyComment.setComment(commentForm.getComment());
        storyComment.setStory(story);
        storyComment.setUser(user);
        storyComment.setDateCreate(new Date());
        storyCommentRepository.save(storyComment);
    }

    @Override
    public long getTotalComment(long storyId) {
        Story story = new Story();
        story.setStoryId(storyId);
        return storyCommentRepository.countAllByStory(story);
    }
}
