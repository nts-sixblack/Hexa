package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.Follow;
import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.repository.FollowRepository;
import nts.sixblack.hexa.service.FollowService;
import nts.sixblack.hexa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    FollowRepository followRepository;

    @Autowired
    UserService userService;

    @Override
    public void sendFollow(long userSenderId, long userRecipientId) {
        User userSender = userService.findById(userSenderId);
        User userRecipient = userService.findById(userRecipientId);
        Follow followw = followRepository.findByUserSenderAndUserRecipient(userSender,userRecipient);
        if(followw!=null){
            followRepository.deleteById(followw.getFollowId());
        } else {
            boolean value = userRecipient.getFollowStatus();
            Follow follow = new Follow();
            follow.setStatus(value);
            follow.setUserSender(userSender);
            follow.setUserRecipient(userRecipient);

            followRepository.save(follow);
        }
    }

    @Override
    public List<Follow> requestList(long userId) {
        List<Long> list = followRepository.requestListFollow(userId);
        List<Follow> userList = new ArrayList<Follow>();
        for(Long l:list){

            Follow fol = followRepository.findByFollowId(l);

            Follow follow = new Follow();
            follow.setFollowId(l);

            User user = userService.findById(fol.getUserSender().getUserId());

            User value = new User();
            value.setUserId(user.getUserId());
            value.setName(user.getName());

            follow.setUserSender(value);
            userList.add(follow);
        }
        return userList;
    }

    @Override
    public void accessFollow(long followId) {
        Follow follow = followRepository.findByFollowId(followId);
        follow.setStatus(true);
        followRepository.save(follow);
    }

    @Override
    public void followStatus(long userId) {
        User user = userService.findById(userId);
        if (user.getFollowStatus()==true){
            user.setFollowStatus(false);
        } else {
            List<Follow> list = followRepository.findByUserRecipient(user);
            for (Follow follow:list){
                follow.setStatus(true);
            }
            followRepository.saveAll(list);
            user.setFollowStatus(true);
        }
        userService.save(user);
    }


}
