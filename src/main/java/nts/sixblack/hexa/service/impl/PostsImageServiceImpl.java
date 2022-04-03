package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.config.TimeConfig;
import nts.sixblack.hexa.entity.Posts;
import nts.sixblack.hexa.entity.PostsImage;
import nts.sixblack.hexa.model.PostsImageInfo;
import nts.sixblack.hexa.repository.PostsImageRepository;
import nts.sixblack.hexa.service.PostsImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostsImageServiceImpl implements PostsImageService {
    @Autowired
    PostsImageRepository postsImageRepository;

    @Override
    public PostsImage save(PostsImage postsImage) {
        postsImage.setDateCreate(new Date());
        return postsImageRepository.save(postsImage);
    }

    @Override
    public List<PostsImageInfo> findListImageByPostsId(long postsId) {
        Posts posts = new Posts();
        posts.setPostsId(postsId);
        List<PostsImage> postsImageList = postsImageRepository.findPostsImageByPosts(posts);
        List<PostsImageInfo> postsImageInfoList = new ArrayList<PostsImageInfo>();
        for (PostsImage postsImage:postsImageList){
            PostsImageInfo postsImageInfo = new PostsImageInfo();
            postsImageInfo.setPostsImageId(postsImage.getPostsImageId());
            postsImageInfo.setImage(postsImage.getImage());
            postsImageInfo.setDateCreate(TimeConfig.getTime(postsImage.getDateCreate()));

            postsImageInfoList.add(postsImageInfo);
        }
        return postsImageInfoList;
    }
}
