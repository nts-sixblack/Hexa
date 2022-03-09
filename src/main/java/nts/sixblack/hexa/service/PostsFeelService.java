package nts.sixblack.hexa.service;

import nts.sixblack.hexa.entity.PostsFeel;
import nts.sixblack.hexa.form.Like;
import nts.sixblack.hexa.model.PostsFeelInfo;

import java.util.List;

public interface PostsFeelService {
    long checkFeel(long postsId, long userId);
    void delete(long postsFeelId);
    void save(PostsFeel postsFeel);
    void like(Like like);
    List<PostsFeelInfo> findListFeelByPostsId(long postsId);
}
