package nts.sixblack.hexa.controller;

import nts.sixblack.hexa.entity.Follow;
import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.model.FollowInfo;
import nts.sixblack.hexa.model.UserInfo;
import nts.sixblack.hexa.service.FollowService;
import nts.sixblack.hexa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void sendRequestFollow(@PathVariable("userSenderId") long userSenderId, @PathVariable("userRecipientId") long userRecipientId){
        followService.sendRequestFollow(userSenderId,userRecipientId);
    }

//    xem danh sách yêu cầu follow
    @GetMapping("{userId}/request")
    public List<FollowInfo> reQuestListFollow(@PathVariable("userId") long userId){
        return followService.requestList(userId);
    }

//    xem danh sách những người mình đang follow
    @GetMapping("{userId}/follower")
    public List<FollowInfo> listFollower(@PathVariable("userId") long userId){
        return followService.followerList(userId);
    }

    @GetMapping("{userId}/following")
    public List<FollowInfo> listFollowing(@PathVariable("userId") long userId){
        return followService.followingList(userId);
    }

    @GetMapping("{userId}/followStatus")
    public void changeFollowStatus(@PathVariable("userId") long userId){
        userService.changeFollowStatus(userId);
    }
}
