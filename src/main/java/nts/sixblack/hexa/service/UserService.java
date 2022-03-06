package nts.sixblack.hexa.service;

import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.form.LoginForm;
import nts.sixblack.hexa.form.RegisterForm;

import java.util.List;

public interface UserService {
    public User login(LoginForm loginForm);
    public User register(RegisterForm registerForm);
    public List<User> findByName(String name);
    public User findById(long userId);
    public void save(User user);
}
