package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.config.TimeConfig;
import nts.sixblack.hexa.entity.Posts;
import nts.sixblack.hexa.entity.PostsComment;
import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.form.CommentForm;
import nts.sixblack.hexa.model.PostsCommentInfo;
import nts.sixblack.hexa.repository.PostsCommentRepository;
import nts.sixblack.hexa.repository.PostsRepository;
import nts.sixblack.hexa.service.PostsCommentService;
import nts.sixblack.hexa.service.PostsService;
import nts.sixblack.hexa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostsCommentServiceImpl implements PostsCommentService {
    @Autowired
    PostsCommentRepository postsCommentRepository;

    @Override
    public void comment(CommentForm commentForm) {
        User user = new User();
        user.setUserId(commentForm.getUserId());
        Posts posts = new Posts();
        posts.setPostsId(commentForm.getTusId());

        PostsComment postsComment = new PostsComment();
        postsComment.setComment(commentForm.getComment());
        postsComment.setPosts(posts);
        postsComment.setUser(user);
        postsComment.setDateCreate(new Date());
        postsCommentRepository.save(postsComment);
    }

    @Override
    public void delete(long postsCommentId) {
        postsCommentRepository.deleteById(postsCommentId);
    }

    @Override
    public List<PostsCommentInfo> findListCommentByPostsId(long postsId) {
        Posts posts = new Posts();
        posts.setPostsId(postsId);
        List<PostsComment> postsCommentList = postsCommentRepository.findPostsCommentByPosts(posts);

        List<PostsCommentInfo> postsCommentInfoList = new ArrayList<PostsCommentInfo>();
        for (PostsComment postsComment:postsCommentList){
            PostsCommentInfo postsCommentInfo = new PostsCommentInfo();
            postsCommentInfo.setPostsCommentId(postsComment.getPostsCommentId());
            postsCommentInfo.setComment(postsComment.getComment());
            postsCommentInfo.setUserId(postsComment.getUser().getUserId());
            postsCommentInfo.setImage(postsComment.getUser().getAvatar());
            postsCommentInfo.setName(postsComment.getUser().getName());
            postsCommentInfo.setDateCreate(postsComment.getDateCreate());

            postsCommentInfoList.add(postsCommentInfo);
        }

        return postsCommentInfoList;
    }
}
