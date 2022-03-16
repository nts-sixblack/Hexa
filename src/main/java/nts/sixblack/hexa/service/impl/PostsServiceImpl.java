package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.*;
import nts.sixblack.hexa.form.Like;
import nts.sixblack.hexa.form.PostsForm;
import nts.sixblack.hexa.model.*;
import nts.sixblack.hexa.repository.PostsImageRepository;
import nts.sixblack.hexa.repository.PostsRepository;
import nts.sixblack.hexa.repository.PostsUserRepository;
import nts.sixblack.hexa.service.*;
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
    PostsUserRepository postsUserRepository;

    @Autowired
    PostsUserService postsUserService;

    @Autowired
    PostsCommentService postsCommentService;

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

    @Override
    public PostsInfo findPostsById(long postsId) {
        Posts posts = postsRepository.findByPostsId(postsId);

        PostsInfo postsInfo = new PostsInfo();
        postsInfo.setPostsId(posts.getPostsId());
        postsInfo.setCaption(posts.getCaption());

        List<PostsUserInfo> postsUserInfoList = postsUserService.findListPostsUserByPostId(postsId);
        postsInfo.setPostsUserList(postsUserInfoList);

        List<PostsCommentInfo> postsCommentInfoList = postsCommentService.findListCommentByPostsId(postsId);
//        postsInfo.setPostsCommentList(postsCommentInfoList);
        postsInfo.setTotalComment(postsCommentInfoList.size());

        List<PostsFeelInfo> postsFeelInfoList = postsFeelService.findListFeelByPostsId(postsId);
//        postsInfo.setPostsFeelList(postsFeelInfoList);
        postsInfo.setTotalFeel(postsFeelInfoList.size());

        List<PostsImageInfo> postsImageInfoList = postsImageService.findListImageByPostsId(postsId);
        postsInfo.setPostsImageList(postsImageInfoList);

        return postsInfo;
    }

    @Override
    public List<PostsInfo> findListPostsByUserId(long userId) {
        User user = new User();
        user.setUserId(userId);
        List<Posts> postsList = postsUserRepository.findPostsByUser(user);
        List<PostsInfo> postsInfoList = new ArrayList<PostsInfo>();
        for (Posts posts:postsList){
            PostsInfo postsInfo = findPostsById(posts.getPostsId());
            postsInfoList.add(postsInfo);
        }
        return postsInfoList;
//        return null;
    }


}
