package nts.sixblack.hexa.controller;

import nts.sixblack.hexa.entity.Follow;
import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.model.FollowInfo;
import nts.sixblack.hexa.model.ResponseObject;
import nts.sixblack.hexa.model.UserInfo;
import nts.sixblack.hexa.service.FollowService;
import nts.sixblack.hexa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("follow")
public class FollowController {

    @Autowired
    FollowService followService;

    @Autowired
    UserService userService;

//    userSender gửi req follow/unfollow đến userRecipient
    @GetMapping("{userSenderId}/{userRecipientId}")
    public ResponseEntity<ResponseObject> sendRequestFollow(@PathVariable("userSenderId") long userSenderId, @PathVariable("userRecipientId") long userRecipientId){
        followService.sendRequestFollow(userSenderId,userRecipientId);
        return ResponseEntity.status(HttpStatus.OK).body(
          new ResponseObject("ok","Gửi request follow", "ok")
        );
    }

//    xem danh sách yêu cầu follow
    @GetMapping("{userId}/request")
    public ResponseEntity<ResponseObject> reQuestListFollow(@PathVariable("userId") long userId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Danh sách người yêu cầu follow", followService.requestList(userId))
        );
    }

//    xem danh sách những người mình đang follow
    @GetMapping("{userId}/follower")
    public ResponseEntity<ResponseObject> listFollower(@PathVariable("userId") long userId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Danh sách người follow", followService.followerList(userId))
        );
    }

    @GetMapping("{userId}/following")
    public ResponseEntity<ResponseObject> listFollowing(@PathVariable("userId") long userId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Danh sách người đang theo dõi tôi", followService.followingList(userId))
        );
    }

    @GetMapping("{userId}/followStatus")
    public ResponseEntity<ResponseObject> changeFollowStatus(@PathVariable("userId") long userId){

        userService.changeFollowStatus(userId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Đã thay đổi trang thái followStatus", "ok")
        );
    }

    @GetMapping("delete/{followId}")
    public ResponseEntity<ResponseObject> deleteRequest(@PathVariable("followId") long followId){
        followService.delete(followId);
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok","Đã xóa", "")
        );
    }
}
