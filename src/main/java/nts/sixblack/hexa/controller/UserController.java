package nts.sixblack.hexa.controller;

import nts.sixblack.hexa.config.TimeConfig;
import nts.sixblack.hexa.form.*;
import nts.sixblack.hexa.jwt.JwtTokenProvider;
import nts.sixblack.hexa.model.FollowInfo;
import nts.sixblack.hexa.model.ResponseObject;
import nts.sixblack.hexa.model.UserInfo;
import nts.sixblack.hexa.service.UserService;
import nts.sixblack.hexa.ultil.CustomUserDetail;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

        System.out.println("login "+userInfo.getUserId());

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

            System.out.println("register "+userInfo.getUserId());
            return login(loginForm);

        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(
                    new ResponseObject("faul","Đăng ký thất bại", "Đã tồn tại email")
            );
        }

    }

    @GetMapping("{userId}")
    public ResponseEntity<ResponseObject> informationOfUser(@PathVariable("userId") long userId){
        long myUserId = getUserId();

//        System.out.println(myUserId);
//        if (myUserId == 0){
//            UserInfo userInfo = userService.information(userId);
//            if (userInfo!=null){
//                return ResponseEntity.status(HttpStatus.OK).body(
//                        new ResponseObject("ok","Thông tin người dùng", userInfo )
//                );
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                        new ResponseObject("ok","Không tìm thấy người này", "" )
//                );
//            }
//        } else {
            UserInfo userInfo = userService.moreInformation(myUserId, userId);
        System.out.println("information user "+userId);
            if (userInfo!=null){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok","Thông tin người dùng", userInfo )
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("ok","Không tìm thấy người này", "" )
                );
            }
//        }


    }

    @GetMapping("/find/name={value}")
    public ResponseEntity<ResponseObject> findByName(@PathVariable("value") String name){
        System.out.println("find like name "+name);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Danh sách người dùng theo tên", userService.getUserByName(name) )
        );
    }



    @PostMapping("/updateAvatar")
    public ResponseEntity<ResponseObject> updateAvatar(@ModelAttribute("userImageForm") UserImageForm userImageForm){

        long userId = getUserId();
        userImageForm.setUserId(userId);

        userService.updateAvatar(userImageForm);

        System.out.println("update avatar "+userImageForm.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Đã cập nhập avatar", "")
        );
    }

    @DeleteMapping("/deleteAvatar")
    public ResponseEntity<ResponseObject> deleteAvatar(){

        long userId = getUserId();
        userService.deteleAvatar(userId);
        System.out.println("delete avatar "+userId);


        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Đã xóa avatar", "")
        );
    }

    @PostMapping("/updateBackground")
    public ResponseEntity<ResponseObject> updateBackground(@ModelAttribute("userImageForm") UserImageForm userImageForm){
        long userId = getUserId();
        userImageForm.setUserId(userId);

        userService.updateBackground(userImageForm);
        System.out.println("update background "+userImageForm.getUserId());

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Đã cập nhập ảnh nền", "")
        );
    }

    @DeleteMapping("/deleteBackground")
    public ResponseEntity<ResponseObject> deleteBackground(){
        long userId = getUserId();

        userService.deteleBackground(userId);
        System.out.println("delete background "+userId);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Đã xóa ảnh nền", "")
        );
    }

    @PostMapping("/changeName")
    public ResponseEntity<ResponseObject> changeName(@RequestBody UserNameForm userNameForm){
        System.out.println("change name "+userNameForm.getUserId());

        userService.changeName(userNameForm);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Đã thay đổi tên", "")
        );
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseObject> update(@RequestBody UserForm userForm){
        userService.update(userForm);
        System.out.println("update information user  "+userForm.getUserId());

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


    private long getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null){
            String token = jwtTokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
            return jwtTokenProvider.getUserId(token);
        }
        return 0;
    }

}
