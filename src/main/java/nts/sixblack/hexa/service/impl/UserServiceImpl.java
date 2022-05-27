package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.config.TimeConfig;
import nts.sixblack.hexa.entity.Follow;
import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.form.*;
import nts.sixblack.hexa.model.PostsInfo;
import nts.sixblack.hexa.model.UserInfo;
import nts.sixblack.hexa.repository.UserRepository;
import nts.sixblack.hexa.service.FollowService;
import nts.sixblack.hexa.service.PostsService;
import nts.sixblack.hexa.service.StorageService;
import nts.sixblack.hexa.service.UserService;
import nts.sixblack.hexa.ultil.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostsService postsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    StorageService storageService;

    @Autowired
    FollowService followService;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${application.bucket.name}")
    private String bucketName;

    @Override
    public void changeFollowStatus(long userId) {
        User user = userRepository.findByUserId(userId);
        if (user.getFollowStatus()==true){
            user.setFollowStatus(false);
        } else {
            user.setFollowStatus(true);
            List<Follow> followList = followService.listuserRecipient(userId);
            for (Follow follow:followList){
                follow.setStatus(true);
                followService.save(follow);
            }
        }

        userRepository.save(user);
    }

    @Override
    public UserInfo login(LoginForm loginForm) {
        UserInfo userInfo = new UserInfo();

        User user = userRepository.findByEmailAndPassword(loginForm.getUserName(), loginForm.getPassword());

        if (user!=null){
            userInfo.setUserId(user.getUserId());
            userInfo.setFirstName(user.getFirstName());
            userInfo.setLastName(user.getLastName());
            userInfo.setAvatar(user.getAvatar());
            userInfo.setBackground(user.getBackground());
            userInfo.setEmail(user.getEmail());
            userInfo.setName(user.getName());
            userInfo.setPhone(user.getPhone());
            userInfo.setFollowStatus(user.getFollowStatus());

//            userInfo.setNumberOfFollower(followService.followerList(user.getUserId()).size());
//            userInfo.setNumberOfFollowing(followService.followingList(user.getUserId()).size());
//            userInfo.setNumberOfPosts(postsService.findListPostsByUserId(user.getUserId()).size());

            userInfo.setNumberOfFollower(followService.numberFollower(user.getUserId()));
            userInfo.setNumberOfFollowing(followService.numberFollowing(user.getUserId()));
            userInfo.setNumberOfPosts(postsService.countPostsOfUser(user.getUserId()));

            return userInfo;
        } else {
            return null;
        }

    }

    @Override
    public UserInfo register(RegisterForm registerForm) {
        UserInfo userInfo = new UserInfo();
        User user1 = userRepository.findByEmail(registerForm.getEmail());
        if (user1==null){
            user1 = new User();
            user1.setFirstName(registerForm.getFirstName());
            user1.setLastName(registerForm.getLastName());
            user1.setEmail(registerForm.getEmail());
            user1.setPhone(registerForm.getPhone());
            user1.setPassword(passwordEncoder.encode(registerForm.getPassword()));
            user1.setName(registerForm.getFirstName()+" "+registerForm.getLastName());
            user1.setFollowStatus(true);
            user1.setDateCreate(new Date());

            User user = userRepository.save(user1);

            userInfo.setUserId(user.getUserId());
            userInfo.setFirstName(user.getFirstName());
            userInfo.setLastName(user.getLastName());
            userInfo.setAvatar(user.getAvatar());
            userInfo.setBackground(user.getBackground());
            userInfo.setEmail(user.getEmail());
            userInfo.setName(user.getName());
            userInfo.setPhone(user.getPhone());
            userInfo.setFollowStatus(user.getFollowStatus());

            return userInfo;
        } else {
            return null;
        }
    }

    @Override
    public UserInfo information(long userId) {
        User user = userRepository.findByUserId(userId);
        if(user == null){
            return null;
        }
        UserInfo userInfo = new UserInfo();

        userInfo.setUserId(user.getUserId());
        userInfo.setFirstName(user.getFirstName());
        userInfo.setLastName(user.getLastName());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setBackground(user.getBackground());
        userInfo.setEmail(user.getEmail());
        userInfo.setName(user.getName());
        userInfo.setPhone(user.getPhone());
        userInfo.setFollowStatus(user.getFollowStatus());
        userInfo.setDateCreate(TimeConfig.getTime(user.getDateCreate()));

//        userInfo.setNumberOfFollower(followService.followerList(userId).size());
//        userInfo.setNumberOfFollowing(followService.followingList(userId).size());
//        userInfo.setNumberOfPosts(postsService.findListPostsByUserId(userId).size());

        userInfo.setNumberOfFollower(followService.numberFollower(user.getUserId()));
        userInfo.setNumberOfFollowing(followService.numberFollowing(user.getUserId()));
        userInfo.setNumberOfPosts(postsService.countPostsOfUser(user.getUserId()));

//        if (userInfo.isFollowStatus()==true){
//            List<PostsInfo> postsInfoList = postsService.findListPostsByUserId(userId);
//            userInfo.setPostsList(postsInfoList);
//        }

        return userInfo;
    }

    @Override
    public UserDetails getUserByUserId(long userId) {
        User user = userRepository.findByUserId(userId);
        return new CustomUserDetail(user);
    }

    @Override
    public List<UserInfo> getUserByName(String name) {
        List<User> list = userRepository.findUserByNameContaining(name);
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        for (User user :list){
            UserInfo userInfo = new UserInfo();

            userInfo.setUserId(user.getUserId());
            userInfo.setFirstName(user.getFirstName());
            userInfo.setLastName(user.getLastName());
            userInfo.setAvatar(user.getAvatar());
            userInfo.setBackground(user.getBackground());
            userInfo.setEmail(user.getEmail());
            userInfo.setName(user.getName());
            userInfo.setPhone(user.getPhone());
            userInfo.setFollowStatus(user.getFollowStatus());

            userInfoList.add(userInfo);
        }

        return userInfoList;
    }

    @Override
    public void updateAvatar(UserImageForm userImageForm) {
        String avatar = "https://"+bucketName+".s3."+region+".amazonaws.com/"+storageService.uploadFile(userImageForm.getFile());
        User user = userRepository.findByUserId(userImageForm.getUserId());
        user.setAvatar(avatar);
        userRepository.save(user);
    }

    @Override
    public void deteleAvatar(long userId) {
        User user = userRepository.findByUserId(userId);
        user.setAvatar(null);
        userRepository.save(user);
    }

    @Override
    public void updateBackground(UserImageForm userImageForm) {
        String background = "https://"+bucketName+".s3."+region+".amazonaws.com/"+storageService.uploadFile(userImageForm.getFile());
        User user = userRepository.findByUserId(userImageForm.getUserId());
        user.setBackground(background);
        userRepository.save(user);
    }

    @Override
    public void deteleBackground(long userId) {
        User user = userRepository.findByUserId(userId);
        user.setBackground(null);
        userRepository.save(user);
    }

    @Override
    public void changeName(UserNameForm userNameForm) {
        User user = userRepository.findByUserId(userNameForm.getUserId());
        user.setFirstName(userNameForm.getFirstName());
        user.setLastName(userNameForm.getLastName());
        user.setName(userNameForm.getFirstName()+" "+userNameForm.getLastName());
        userRepository.save(user);
    }

    @Override
    public UserInfo getByEmail(String email) {
        User user = userRepository.findByEmail(email);

        UserInfo userInfo = new UserInfo();

        userInfo.setUserId(user.getUserId());
        userInfo.setFirstName(user.getFirstName());
        userInfo.setLastName(user.getLastName());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setBackground(user.getBackground());
        userInfo.setEmail(user.getEmail());
        userInfo.setName(user.getName());
        userInfo.setPhone(user.getPhone());
        userInfo.setFollowStatus(user.getFollowStatus());

//        userInfo.setNumberOfFollower(followService.followerList(user.getUserId()).size());
//        userInfo.setNumberOfFollowing(followService.followingList(user.getUserId()).size());
//        userInfo.setNumberOfPosts(postsService.findListPostsByUserId(user.getUserId()).size());

        userInfo.setNumberOfFollower(followService.numberFollower(user.getUserId()));
        userInfo.setNumberOfFollowing(followService.numberFollowing(user.getUserId()));
        userInfo.setNumberOfPosts(postsService.countPostsOfUser(user.getUserId()));

        return userInfo;
    }

    @Override
    public void update(UserForm userForm) {
        User user = userRepository.findByUserId(userForm.getUserId());
        System.out.println(user.getName());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setName(user.getFirstName()+" "+user.getLastName());
        user.setPhone(userForm.getPhone());
        user.setFollowStatus(userForm.isFollowStatus());

        userRepository.save(user);
    }

    @Override
    public UserInfo moreInformation(long myUserId, long userId) {

        UserInfo userInfo = information(userId);

        Follow follow = followService.findFollow(myUserId, userId);
        if (follow == null){
            userInfo.setFollow(-1); // chưa follow
        }
        if (follow != null){
            if (follow.isStatus()){
                userInfo.setFollow(1); // đã follow
            } else {
                userInfo.setFollow(0); // đang chờ xác nhận
            }
        }

        return userInfo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user==null){
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetail(user);
    }
}
