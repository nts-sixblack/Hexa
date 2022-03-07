package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.*;
import nts.sixblack.hexa.form.Like;
import nts.sixblack.hexa.form.PostsForm;
import nts.sixblack.hexa.model.PostsImageInfo;
import nts.sixblack.hexa.model.PostsInfo;
import nts.sixblack.hexa.repository.PostsImageRepository;
import nts.sixblack.hexa.repository.PostsRepository;
import nts.sixblack.hexa.service.PostsFeelService;
import nts.sixblack.hexa.service.PostsImageService;
import nts.sixblack.hexa.service.PostsService;
import nts.sixblack.hexa.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {
    @Autowired
    PostsRepository postsRepository;
    @Autowired
    PostsImageService postsImageService;
    @Autowired
    PostsFeelService postsFeelService;
    @Autowired
    StorageService storageService;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${application.bucket.name}")
    private String bucketName;

    @Override
    public PostsInfo newPosts(PostsForm postsForm) {
        Posts posts = new Posts();
        posts.setCaption(postsForm.getCaption());

        PostsImage postsImage = new PostsImage();
        postsImage.setImage("https://"+bucketName+".s3."+region+".amazonaws.com/"+storageService.uploadFile(postsForm.getFiles()));
        postsImage.setPosts(postsRepository.save(posts));
        postsImageService.save(postsImage);

        PostsInfo postsInfo = new PostsInfo();
        postsInfo.setCaption(postsForm.getCaption());
        postsInfo.setPostsId(postsImage.getPosts().getPostsId());

        List<PostsImageInfo> postsImageInfoList = new ArrayList<PostsImageInfo>();
        PostsImageInfo postsImageInfo = new PostsImageInfo();
        postsImageInfo.setImage(postsImage.getImage());
        postsImageInfoList.add(postsImageInfo);

        postsInfo.setPostsImageList(postsImageInfoList);

        return postsInfo;
    }




}
