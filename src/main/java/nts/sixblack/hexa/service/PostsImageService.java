package nts.sixblack.hexa.service;

import nts.sixblack.hexa.entity.PostsImage;
import nts.sixblack.hexa.model.PostsImageInfo;

import java.util.List;

public interface PostsImageService {
    public PostsImage save(PostsImage postsImage);
    List<PostsImageInfo> findListImageByPostsId(long postsId);
}
