package nts.sixblack.hexa.service;

import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.form.LoginForm;
import nts.sixblack.hexa.form.RegisterForm;
import nts.sixblack.hexa.form.UserImageForm;
import nts.sixblack.hexa.form.UserNameForm;
import nts.sixblack.hexa.model.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    void changeFollowStatus(long userId);
    UserInfo login(LoginForm loginForm);
    UserInfo register(RegisterForm registerForm);
    UserInfo information(long userId);
    UserDetails getUserByUserId(long userId);
    List<UserInfo> getUserByName(String name);
    void updateAvatar(UserImageForm userImageForm);
    void deteleAvatar(long userId);
    void updateBackground(UserImageForm userImageForm);
    void deteleBackground(long userId);
    void changeName(UserNameForm userNameForm);
    UserInfo getByEmail(String email);
}
