package nts.sixblack.hexa.controller;

import nts.sixblack.hexa.config.TimeConfig;
import nts.sixblack.hexa.form.*;
import nts.sixblack.hexa.jwt.JwtTokenProvider;
import nts.sixblack.hexa.jwt.JwtValue;
import nts.sixblack.hexa.model.FollowInfo;
import nts.sixblack.hexa.model.MessageInfo;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    JwtValue jwtValue;

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
        UserInfo userInfo = userService.getByEmail(loginForm.getUserName());
        userInfo.setToken(token);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Đăng nhập thành công", userInfo)
        );

//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("ok","Đăng nhập thành công", userService.login(loginForm))
//        );
    }

    @PostMapping("register")
    public ResponseEntity<ResponseObject> register(@RequestBody RegisterForm registerForm){
        UserInfo userInfo = userService.register(registerForm);
        if (userInfo!=null){

            LoginForm loginForm = new LoginForm();
            loginForm.setUserName(registerForm.getEmail());
            loginForm.setPassword(registerForm.getPassword());

            return login(loginForm);

        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(
                    new ResponseObject("faul","Đăng ký thất bại", "Đã tồn tại email")
            );
        }

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
                    new ResponseObject("ok","Không tìm thấy người này", "" )
            );
        }
    }

    @GetMapping("/find/name={value}")
    public ResponseEntity<ResponseObject> findByName(@PathVariable("value") String name){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Danh sách người dùng theo tên", userService.getUserByName(name) )
        );
    }

    @PostMapping("/updateAvatar")
    public ResponseEntity<ResponseObject> updateAvatar(@ModelAttribute("userImageForm") UserImageForm userImageForm){

        userService.updateAvatar(userImageForm);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Đã cập nhập avatar", "")
        );
    }

    @DeleteMapping("/deleteAvatar/{userId}")
    public ResponseEntity<ResponseObject> deleteAvatar(@PathVariable("userId") long userId){

        userService.deteleAvatar(userId);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Đã xóa avatar", "")
        );
    }

    @PostMapping("/updateBackground")
    public ResponseEntity<ResponseObject> updateBackground(@ModelAttribute("userImageForm") UserImageForm userImageForm){

        userService.updateBackground(userImageForm);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Đã cập nhập ảnh nền", "")
        );
    }

    @DeleteMapping("/deleteBackground/{userId}")
    public ResponseEntity<ResponseObject> deleteBackground(@PathVariable("userId") long userId){

        userService.deteleBackground(userId);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Đã xóa ảnh nền", "")
        );
    }

    @PostMapping("/changeName")
    public ResponseEntity<ResponseObject> changeName(@RequestBody UserNameForm userNameForm){

        userService.changeName(userNameForm);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Đã thay đổi tên", "")
        );
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseObject> update(@RequestBody UserForm userForm){
        userService.update(userForm);
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "Đã cập nhập thông tin người dùng", "")
        );
    }

    @GetMapping("/hmmhmm")
    public FollowInfo check(){
        FollowInfo followInfo = new FollowInfo();
        followInfo.setFollowId(1);
        followInfo.setStatus(true);
        followInfo.setUserId(123);
        followInfo.setUserName("SixBlack");
        followInfo.setUserImage("hmm");
        followInfo.setDateCreate(TimeConfig.getTime(new Date()));
        return followInfo;
    }


}
