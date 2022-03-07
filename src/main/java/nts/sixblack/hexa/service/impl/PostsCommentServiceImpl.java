package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.Posts;
import nts.sixblack.hexa.entity.PostsComment;
import nts.sixblack.hexa.entity.User;
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

    @Override
    public void comment(CommentForm commentForm) {
        User user = new User();
        user.setUserId(commentForm.getUserId());
        Posts posts = new Posts();
        posts.setPostsId(commentForm.getPostsId());

        PostsComment postsComment = new PostsComment();
        postsComment.setComment(commentForm.getComment());
        postsComment.setPosts(posts);
        postsComment.setUser(user);
        postsCommentRepository.save(postsComment);
    }

    @Override
    public void delete(long postsCommentId) {
        postsCommentRepository.deleteById(postsCommentId);
    }
}
