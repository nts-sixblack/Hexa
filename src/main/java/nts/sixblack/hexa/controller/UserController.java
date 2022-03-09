package nts.sixblack.hexa.controller;

import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.form.LoginForm;
import nts.sixblack.hexa.form.RegisterForm;
import nts.sixblack.hexa.model.UserInfo;
import nts.sixblack.hexa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("login")
    public UserInfo login(@RequestBody LoginForm loginForm){
        return userService.login(loginForm);
    }

    @PostMapping("register")
    public UserInfo register(@RequestBody RegisterForm registerForm){
        return userService.register(registerForm);
    }

    @GetMapping("{userId}")
    public UserInfo informationOfUser(@PathVariable("userId") long userId){
        return userService.information(userId);
    }


}
