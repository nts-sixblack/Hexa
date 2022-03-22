package nts.sixblack.hexa.service;

import nts.sixblack.hexa.entity.PostsUser;
import nts.sixblack.hexa.model.PostsUserInfo;

import java.util.List;

public interface PostsUserService {
    List<PostsUserInfo> findListPostsUserByPostId(long postsId);
    void save(PostsUser postsUser);
}
