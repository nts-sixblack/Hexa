package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.Posts;
import nts.sixblack.hexa.entity.PostsFeel;
import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.form.Like;
import nts.sixblack.hexa.repository.PostsFeelRepository;
import nts.sixblack.hexa.service.PostsFeelService;
import nts.sixblack.hexa.service.PostsService;
import nts.sixblack.hexa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostsFeelServiceImpl implements PostsFeelService {
    @Autowired
    PostsFeelRepository postsFeelRepository;

    @Override
    public long checkFeel(long postsId, long userId) {
        PostsFeel postsFeel = postsFeelRepository.checkFeelPostsUser(postsId, userId);
        if (postsFeel!=null){
//            tồn tại trong table
            return postsFeel.getPostsFeelId();
        }
        return 0;
    }

    @Override
    public void delete(long postsFeelId) {
        postsFeelRepository.deleteById(postsFeelId);
    }

    @Override
    public void save(PostsFeel postsFeel) {
        postsFeelRepository.save(postsFeel);
    }

    @Override
    public void like(Like like) {
        long postsFeelId = checkFeel(like.getPostsId(),like.getUserId());
        if (postsFeelId > 0){
            delete(postsFeelId);
        } else {
            User user = new User();
            user.setUserId(like.getUserId());
            Posts posts = new Posts();
            posts.setPostsId(like.getPostsId());

            PostsFeel postsFeel = new PostsFeel();
            postsFeel.setFeel(true);
            postsFeel.setUser(user);
            postsFeel.setPosts(posts);

            save(postsFeel);
        }
    }
}
