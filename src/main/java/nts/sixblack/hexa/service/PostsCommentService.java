package nts.sixblack.hexa.service;

import nts.sixblack.hexa.form.CommentForm;

public interface PostsCommentService {
    void comment(CommentForm commentForm);
    void delete(long postsCommentId);
}
