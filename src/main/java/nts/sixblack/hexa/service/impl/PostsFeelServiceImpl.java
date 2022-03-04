package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.repository.PostsFeelRepository;
import nts.sixblack.hexa.service.PostsFeelService;
import org.springframework.beans.factory.annotation.Autowired;

public class PostsFeelServiceImpl implements PostsFeelService {
    @Autowired
    PostsFeelRepository postsFeelRepository;
}
