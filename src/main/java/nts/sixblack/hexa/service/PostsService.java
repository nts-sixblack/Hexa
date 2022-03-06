package nts.sixblack.hexa.service;

import nts.sixblack.hexa.entity.Posts;
import nts.sixblack.hexa.form.PostsForm;

import java.util.List;

public interface PostsService {
    List<Posts> getAll();
    Posts newPosts(PostsForm postsForm);
    Posts findById(long userId);
}
