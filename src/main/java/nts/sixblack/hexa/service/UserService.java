package nts.sixblack.hexa.service;

import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.form.LoginForm;
import nts.sixblack.hexa.form.RegisterForm;
import nts.sixblack.hexa.model.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    void changeFollowStatus(long userId);
    UserInfo login(LoginForm loginForm);
    UserInfo register(RegisterForm registerForm);
    UserInfo information(long userId);
    UserDetails getUserByUserId(long userId);
}
