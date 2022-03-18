package nts.sixblack.hexa.controller;

import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.form.LoginForm;
import nts.sixblack.hexa.form.RegisterForm;
import nts.sixblack.hexa.jwt.JwtTokenProvider;
import nts.sixblack.hexa.model.ResponseObject;
import nts.sixblack.hexa.model.UserInfo;
import nts.sixblack.hexa.service.UserService;
import nts.sixblack.hexa.ultil.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("login")
    public ResponseEntity<ResponseObject> login(@RequestBody LoginForm loginForm){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginForm.getUserName(),
                        loginForm.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Đăng nhập thành công", token)
        );

//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("ok","Đăng nhập thành công", userService.login(loginForm))
//        );
    }

    @PostMapping("register")
    public ResponseEntity<ResponseObject> register(@RequestBody RegisterForm registerForm){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Đăng ký thành công", userService.register(registerForm))
        );
    }

    @GetMapping("{userId}")
    public ResponseEntity<ResponseObject> informationOfUser(@PathVariable("userId") long userId){
        UserInfo userInfo = userService.information(userId);
        if (userInfo!=null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Thông tin người dùng", userInfo )
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("ok","Thông tin người dùng", userInfo )
            );
        }

    }


}
