package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.repository.PostsRepository;
import nts.sixblack.hexa.service.PostsCommentService;
import org.springframework.beans.factory.annotation.Autowired;

public class PostsCommentServiceImpl implements PostsCommentService {
    @Autowired
    PostsRepository postsRepository;
}
