package nts.sixblack.hexa.service;

import nts.sixblack.hexa.form.PostsForm;
import nts.sixblack.hexa.model.PostsInfo;

import java.util.List;

public interface PostsService {
    PostsInfo newPosts(PostsForm postsForm);
    PostsInfo findPostsById(long postsId);
    List<PostsInfo> findListPostsByUserId(long userId);
    List<PostsInfo> findListPostsByUserId(long userId, int page);
    void delete(long postsId);
    List<PostsInfo> listPostShow(long userId);
    List<PostsInfo> listPostShow(long userId, int page);
    PostsInfo findPostByUser(long postsId, long userId);
    List<PostsInfo> getAll();
    int countPostsOfUser(long userId);

    List<PostsInfo> listNumberPosts(int page, int number);
}
