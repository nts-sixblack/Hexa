package nts.sixblack.hexa.controller;

import nts.sixblack.hexa.entity.Follow;
import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.model.UserInfo;
import nts.sixblack.hexa.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    FollowService followService;

//    userSender follow userRecipient
    @GetMapping("{sender}/{recipient}")
    public void follow(@PathVariable("sender") long userSender, @PathVariable("recipient") long userRecipient){
        followService.sendFollow(userSender, userRecipient);
    }

//    request list of another people(userId)
    @GetMapping("following/{userId}")
    public List<Follow> requesList(@PathVariable("userId") long userId){
        return followService.requestList(userId);
    }

//    accept follow
    @GetMapping("accept/{followId}")
    public void access(@PathVariable("followId") long followId){
        followService.accessFollow(followId);
    }

//    turn on/off follow status of people
    @GetMapping("user/{userId}")
    public void followStatus(@PathVariable("userId") long userId){
        followService.followStatus(userId);
    }

}
