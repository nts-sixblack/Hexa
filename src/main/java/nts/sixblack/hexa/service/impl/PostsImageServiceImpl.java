package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.repository.PostsImageRepository;
import nts.sixblack.hexa.service.PostsImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostsImageServiceImpl implements PostsImageService {
    @Autowired
    PostsImageRepository postsImageRepository;
}
