package nts.sixblack.hexa.service;

import nts.sixblack.hexa.form.PostsForm;
import nts.sixblack.hexa.model.PostsInfo;

import java.util.List;

public interface PostsService {
    PostsInfo newPosts(PostsForm postsForm);
    PostsInfo findPostsById(long postsId);
    List<PostsInfo> findListPostsByUserId(long userId);
    void delete(long postsId);
    List<PostsInfo> listPostShow(long userId);
    PostsInfo findPostByUser(long postsId, long userId);
    List<PostsInfo> getAll();
}
