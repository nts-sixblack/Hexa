package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.Posts;
import nts.sixblack.hexa.entity.PostsUser;
import nts.sixblack.hexa.model.PostsUserInfo;
import nts.sixblack.hexa.repository.PostsUserRepository;
import nts.sixblack.hexa.service.PostsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostsUserServiceImpl implements PostsUserService {
    @Autowired
    PostsUserRepository postsUserRepository;

    @Override
    public List<PostsUserInfo> findListPostsUserByPostId(long postsId) {
        Posts posts = new Posts();
        posts.setPostsId(postsId);
        List<PostsUser> postsUserList = postsUserRepository.findPostsUserByPosts(posts);

        List<PostsUserInfo> postsUserInfoList = new ArrayList<PostsUserInfo>();
        for (PostsUser postsUser:postsUserList){
            PostsUserInfo postsUserInfo = new PostsUserInfo();
            postsUserInfo.setPostsUserId(postsUser.getPostUserId());
            postsUserInfo.setUserId(postsUser.getUser().getUserId());
            postsUserInfo.setImage(postsUser.getUser().getAvatar());
            postsUserInfo.setName(postsUser.getUser().getName());

            postsUserInfoList.add(postsUserInfo);
        }

        return postsUserInfoList;
    }
}
