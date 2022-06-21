package nts.sixblack.hexa.controller;

import nts.sixblack.hexa.entity.Follow;
import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.jwt.JwtTokenProvider;
import nts.sixblack.hexa.model.FollowInfo;
import nts.sixblack.hexa.model.ResponseObject;
import nts.sixblack.hexa.model.UserInfo;
import nts.sixblack.hexa.service.FollowService;
import nts.sixblack.hexa.service.UserService;
import nts.sixblack.hexa.ultil.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("follow")
public class FollowController {

    @Autowired
    FollowService followService;

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

//    userSender gửi req follow/unfollow đến userRecipient
    @GetMapping("send/{userRecipientId}")
    public ResponseEntity<ResponseObject> sendRequestFollow(@PathVariable("userRecipientId") long userRecipientId){
        long userSenderId = getUserId();
        followService.sendRequestFollow(userSenderId,userRecipientId);
        System.out.println("send request follow");
        return ResponseEntity.status(HttpStatus.OK).body(
          new ResponseObject("ok","Gửi request follow", "ok")
        );
    }

//    xem danh sách yêu cầu follow
    @GetMapping("request")
    public ResponseEntity<ResponseObject> reQuestListFollow(){
        long userId = getUserId();
        System.out.println("list request follow "+userId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Danh sách người yêu cầu follow", followService.requestList(userId))
        );
    }

    @GetMapping("request/{page}")
    public ResponseEntity<ResponseObject> reQuestListFollow(@PathVariable("page") int page){
        long userId = getUserId();
        System.out.println("list request follow "+userId+", page:"+page);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Danh sách người yêu cầu follow", followService.requestList(userId, page))
        );
    }

//    xem danh sách những người mình đang follow
    @GetMapping("follower")
    public ResponseEntity<ResponseObject> listFollower(){
        long userId = getUserId();
        System.out.println("list follower "+userId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Danh sách người tôi đang theo dõi", followService.followerList(userId))
        );
    }

    @GetMapping("following")
    public ResponseEntity<ResponseObject> listFollowing(){
        long userId = getUserId();
        System.out.println("list following "+userId);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Danh sách người đang theo dõi tôi", followService.followingList(userId))
        );
    }

    @GetMapping("followStatus")
    public ResponseEntity<ResponseObject> changeFollowStatus(){
        long userId = getUserId();
        userService.changeFollowStatus(userId);
        System.out.println("change follow status");
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Đã thay đổi trang thái followStatus", "ok")
        );
    }

    @GetMapping("accept/{followId}")
    public ResponseEntity<ResponseObject> acceptRequest(@PathVariable("followId") long followId){
        followService.accept(followId);
        System.out.println("accept follow "+followId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Đã xác nhận","")
        );
    }

    @GetMapping("delete/{followId}")
    public ResponseEntity<ResponseObject> deleteRequest(@PathVariable("followId") long followId){
        followService.delete(followId);
        System.out.println("delete follow"+followId);
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok","Đã xóa", "")
        );
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
