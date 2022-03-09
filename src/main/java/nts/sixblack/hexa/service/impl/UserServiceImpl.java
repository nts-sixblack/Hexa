package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.form.LoginForm;
import nts.sixblack.hexa.form.RegisterForm;
import nts.sixblack.hexa.model.PostsInfo;
import nts.sixblack.hexa.model.UserInfo;
import nts.sixblack.hexa.repository.UserRepository;
import nts.sixblack.hexa.service.PostsService;
import nts.sixblack.hexa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostsService postsService;

    @Override
    public void changeFollowStatus(long userId) {
        User user = userRepository.findByUserId(userId);
        if (user.getFollowStatus()==true){
            user.setFollowStatus(false);
        } else {
            user.setFollowStatus(true);
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
            user1.setPassword(registerForm.getPassword());
            user1.setName(registerForm.getFirstName()+" "+registerForm.getLastName());
            user1.setFollowStatus(true);

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

        if (userInfo.isFollowStatus()==true){
            List<PostsInfo> postsInfoList = postsService.findListPostsByUserId(userId);
            userInfo.setPostsList(postsInfoList);
        } else {

        }

        return userInfo;
    }
}
