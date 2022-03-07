package nts.sixblack.hexa.service;

import nts.sixblack.hexa.entity.Posts;
import nts.sixblack.hexa.form.Like;
import nts.sixblack.hexa.form.PostsForm;
import nts.sixblack.hexa.model.PostsInfo;

import java.util.List;

public interface PostsService {
    PostsInfo newPosts(PostsForm postsForm);
}
