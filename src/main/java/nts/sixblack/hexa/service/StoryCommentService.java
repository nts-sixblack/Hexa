package nts.sixblack.hexa.service;

import nts.sixblack.hexa.form.CommentForm;

public interface StoryCommentService {

    void comment(CommentForm commentForm);

    long getTotalComment(long storyId);
}
