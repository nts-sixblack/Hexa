package nts.sixblack.hexa.service;

import nts.sixblack.hexa.form.CommentForm;

public interface PostsCommentService {
    public boolean comment(CommentForm commentForm);
    public void deleteComment(long postsCommmentId);
}
