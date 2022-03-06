package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.Posts;
import nts.sixblack.hexa.entity.PostsImage;
import nts.sixblack.hexa.form.PostsForm;
import nts.sixblack.hexa.repository.PostsImageRepository;
import nts.sixblack.hexa.repository.PostsRepository;
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
    PostsImageRepository postsImageRepository;
    @Autowired
    StorageService storageService;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${application.bucket.name}")
    private String bucketName;

    @Override
    public List<Posts> getAll() {
        return postsRepository.findAll();
    }

    @Override
    public Posts newPosts(PostsForm postsForm) {
        Posts posts = new Posts();
        posts.setCaption(postsForm.getCaption());

        Posts posts1 = postsRepository.save(posts);
        List<PostsImage> postsImageList = new ArrayList<PostsImage>();
        PostsImage postsImage = new PostsImage();
        postsImage.setImage("https://"+bucketName+".s3."+region+".amazonaws.com/"+storageService.uploadFile(postsForm.getFiles()));
        postsImage.setPosts(posts1);

        postsImageList.add(postsImage);
        postsImageRepository.saveAll(postsImageList);

        return posts1;
    }

    public Posts findById(long postsId){
        return postsRepository.findById(postsId).get();
    }
}
