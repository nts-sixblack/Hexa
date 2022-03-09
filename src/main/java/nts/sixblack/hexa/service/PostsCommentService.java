package nts.sixblack.hexa.service;

import nts.sixblack.hexa.form.CommentForm;
import nts.sixblack.hexa.model.PostsCommentInfo;

import java.util.List;

public interface PostsCommentService {
    void comment(CommentForm commentForm);
    void delete(long postsCommentId);
    List<PostsCommentInfo> findListCommentByPostsId(long postsId);
}
