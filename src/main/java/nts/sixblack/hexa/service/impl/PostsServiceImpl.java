package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.repository.PostsRepository;
import nts.sixblack.hexa.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;

public class PostsServiceImpl implements PostsService {
    @Autowired
    PostsRepository postsRepository;
}
