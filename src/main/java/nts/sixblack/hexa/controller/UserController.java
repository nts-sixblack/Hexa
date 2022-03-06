package nts.sixblack.hexa.controller;

import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.form.LoginForm;
import nts.sixblack.hexa.form.RegisterForm;
import nts.sixblack.hexa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("name={name}")
    public List<User> findByName(@PathVariable("name") String name){
        return userService.findByName(name);
    }

    @GetMapping("id={userId}")
    public User findById(@PathVariable("userId") Long userId){
        return userService.findById(userId);
    }

    @PostMapping("login")
    public User login(@RequestBody LoginForm loginForm){
        return userService.login(loginForm);
    }

    @PostMapping("register")
    public User register(@RequestBody RegisterForm registerForm){
        return userService.register(registerForm);
    }



}
