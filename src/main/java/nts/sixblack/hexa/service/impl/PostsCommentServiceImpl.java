package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.PostsComment;
import nts.sixblack.hexa.form.CommentForm;
import nts.sixblack.hexa.repository.PostsCommentRepository;
import nts.sixblack.hexa.repository.PostsRepository;
import nts.sixblack.hexa.service.PostsCommentService;
import nts.sixblack.hexa.service.PostsService;
import nts.sixblack.hexa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostsCommentServiceImpl implements PostsCommentService {
    @Autowired
    PostsCommentRepository postsCommentRepository;
    @Autowired
    UserService userService;
    @Autowired
    PostsService postsService;

    @Override
    public boolean comment(CommentForm commentForm) {
        PostsComment postsComment = new PostsComment();
        postsComment.setComment(commentForm.getComment());
        postsComment.setPosts(postsService.findById(commentForm.getPostsId()));
        postsComment.setUser(userService.findById(commentForm.getUserId()));
        postsCommentRepository.save(postsComment);
        return true;
    }

    @Override
    public void deleteComment(long postsCommmentId) {
        postsCommentRepository.deleteById(postsCommmentId);
    }
}
