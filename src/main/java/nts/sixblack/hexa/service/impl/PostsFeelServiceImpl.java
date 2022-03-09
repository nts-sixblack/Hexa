package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.Posts;
import nts.sixblack.hexa.entity.PostsFeel;
import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.form.Like;
import nts.sixblack.hexa.model.PostsFeelInfo;
import nts.sixblack.hexa.repository.PostsFeelRepository;
import nts.sixblack.hexa.service.PostsFeelService;
import nts.sixblack.hexa.service.PostsService;
import nts.sixblack.hexa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        long postsFeelId = checkFeel(like.getTusId(),like.getUserId());
        if (postsFeelId > 0){
            delete(postsFeelId);
        } else {
            User user = new User();
            user.setUserId(like.getUserId());
            Posts posts = new Posts();
            posts.setPostsId(like.getTusId());

            PostsFeel postsFeel = new PostsFeel();
            postsFeel.setFeel(true);
            postsFeel.setUser(user);
            postsFeel.setPosts(posts);

            save(postsFeel);
        }
    }

    @Override
    public List<PostsFeelInfo> findListFeelByPostsId(long postsId) {
        Posts posts = new Posts();
        posts.setPostsId(postsId);
        List<PostsFeel> postsFeelList = postsFeelRepository.findPostsFeelByPosts(posts);
        List<PostsFeelInfo> postsFeelInfoList = new ArrayList<PostsFeelInfo>();
        for (PostsFeel postsFeel:postsFeelList){
            PostsFeelInfo postsFeelInfo = new PostsFeelInfo();
            postsFeelInfo.setPostsFeelId(postsFeel.getPostsFeelId());
            postsFeelInfo.setFeel(postsFeel.isFeel());
            postsFeelInfo.setPostsId(postsFeel.getPosts().getPostsId());

            postsFeelInfoList.add(postsFeelInfo);
        }

        return postsFeelInfoList;
    }
}
