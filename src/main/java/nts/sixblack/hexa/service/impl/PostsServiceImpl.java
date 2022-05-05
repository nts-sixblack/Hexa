package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.config.TimeConfig;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    FollowService followService;


    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${application.bucket.name}")
    private String bucketName;

    @Override
    public PostsInfo newPosts(PostsForm postsForm) {
        User user = new User();
        user.setUserId(postsForm.getUserId());

        Posts posts = new Posts();
        posts.setCaption(postsForm.getCaption());
        posts.setDateCreate(new Date());
        Posts p = postsRepository.save(posts);

        PostsImage postsImage = new PostsImage();
        postsImage.setImage("https://"+bucketName+".s3."+region+".amazonaws.com/"+storageService.uploadFile(postsForm.getFiles()));
        postsImage.setPosts(p);
        postsImageService.save(postsImage);

        PostsUser postsUser = new PostsUser();
        postsUser.setUser(user);
        postsUser.setPosts(p);
        postsUser.setDateCreate(new Date());
        postsUserRepository.save(postsUser);

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
        postsInfo.setDateCreate(TimeConfig.getTime(posts.getDateCreate()));

        postsInfo.setPostsUserList(postsUserService.findListPostsUserByPostId(postsId));

//        postsInfo.setPostsCommentList(postsCommentInfoList);
        postsInfo.setTotalComment(postsCommentService.findListCommentByPostsId(postsId).size());

//        postsInfo.setPostsFeelList(postsFeelInfoList);
        postsInfo.setTotalFeel(postsFeelService.findListFeelByPostsId(postsId).size());

        postsInfo.setPostsImageList(postsImageService.findListImageByPostsId(postsId));

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
    }

    @Override
    public List<PostsInfo> findListPostsByUserId(long userId, int page) {
        User user = new User();
        user.setUserId(userId);
        Pageable pageable = PageRequest.of(page, 5);
        List<Posts> postsList = postsUserRepository.findPostsByUser(user, pageable);
        List<PostsInfo> postsInfoList = new ArrayList<PostsInfo>();
        for (Posts posts:postsList){
            PostsInfo postsInfo = findPostsById(posts.getPostsId());
            postsInfoList.add(postsInfo);
        }
        return postsInfoList;
    }

    @Override
    public void delete(long postsId) {
        postsRepository.deleteById(postsId);
    }

    @Override
    public List<PostsInfo> listPostShow(long userId) {
        List<Long> userIdList = followService.listUserRecipient(userId);
        List<PostsInfo> postsInfoList = new ArrayList<PostsInfo>();
        for (Long id:userIdList){
            List<PostsInfo> list  = findListPostsByUserId(id);
            postsInfoList.addAll(list);
        }

        return postsInfoList;
    }

    @Override
    public List<PostsInfo> listPostShow(long userId, int page) {
        User user = new User();
        user.setUserId(userId);
        Pageable pageable = PageRequest.of(page, 5);

        List<Long> postsList = postsRepository.listNumberPostShow(user, pageable);
        List<PostsInfo> postsInfoList = new ArrayList<PostsInfo>();
        for (Long postsId:postsList){
            PostsInfo postsInfo = findPostsById(postsId);

            postsInfoList.add(postsInfo);
        }

        return postsInfoList;
    }


    @Override
    public PostsInfo findPostByUser(long postsId, long userId) {
        PostsInfo postsInfo = findPostsById(postsId);
        if (postsFeelService.checkFeel(postsId, userId) > 0){
            postsInfo.setFeel(true);
        } else {
            postsInfo.setFeel(false);
        }
        return postsInfo;
    }

    ////////////////
    @Override
    public List<PostsInfo> getAll() {
        List<PostsInfo> postsInfoList = new ArrayList<PostsInfo>();
        List<Posts> postsList = postsRepository.getAll();
        for (Posts posts:postsList){
            PostsInfo postsInfo = findPostsById(posts.getPostsId());
            postsInfoList.add(postsInfo);
        }
        return postsInfoList;
    }

    @Override
    public List<PostsInfo> listNumberPosts(int page, int size) {
        List<PostsInfo> postsInfoList = new ArrayList<PostsInfo>();
        Pageable pageable = PageRequest.of(page, size);
        List<Posts> postsList = postsRepository.listNumberPost(pageable);
        for (Posts posts:postsList){
            PostsInfo postsInfo = findPostsById(posts.getPostsId());
            postsInfoList.add(postsInfo);
        }
        return postsInfoList;
    }
    //////////////////


}
