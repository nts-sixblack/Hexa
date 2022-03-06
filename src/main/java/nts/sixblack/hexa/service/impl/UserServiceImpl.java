package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.form.LoginForm;
import nts.sixblack.hexa.form.RegisterForm;
import nts.sixblack.hexa.repository.UserRepository;
import nts.sixblack.hexa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User login(LoginForm loginForm) {
        User user = userRepository.findByEmailAndPassword(loginForm.getUserName(), loginForm.getPassword());
        if (user!=null){
            return user;
        }
        return null;
    }

    @Override
    public User register(RegisterForm registerForm) {
        if (userRepository.findByEmail(registerForm.getEmail())!=null){
            User user = new User();
            user.setEmail(registerForm.getEmail());
            user.setFirstName(registerForm.getFirstName());
            user.setLastName(registerForm.getLastName());
            user.setPassword(registerForm.getPassword());
            user.setPhone(registerForm.getPhone());
            user.setFollowStatus(true);
            user.setName(registerForm.getFirstName()+" "+registerForm.getLastName());

            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findLikeName(name);
    }

    @Override
    public User findById(long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
