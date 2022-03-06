package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.repository.PostsUserRepository;
import nts.sixblack.hexa.service.PostsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostsUserServiceImpl implements PostsUserService {
    @Autowired
    PostsUserRepository postsUserRepository;
}
