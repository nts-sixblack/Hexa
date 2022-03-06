package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.PostsFeel;
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
    @Autowired
    UserService userService;
    @Autowired
    PostsService postsService;

    @Override
    public boolean checkFeel(long userId, long postsId) {
        PostsFeel postsFeel = postsFeelRepository.checkFeelPostsUser(postsId, userId);
        if (postsFeel != null){
            postsFeelRepository.deleteById(postsFeel.getPostsFeelId());
            return false;
        } else {
            PostsFeel postsFeel1 = new PostsFeel();
            postsFeel1.setPosts(postsService.findById(postsId));
            postsFeel1.setFeel(true);
            postsFeel1.setUser(userService.findById(userId));
            postsFeelRepository.save(postsFeel1);
            return true;
        }
    }
}
