package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.Follow;
import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.model.FollowInfo;
import nts.sixblack.hexa.model.UserInfo;
import nts.sixblack.hexa.repository.FollowRepository;
import nts.sixblack.hexa.repository.UserRepository;
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
    UserRepository userRepository;


    @Override
    public void sendRequestFollow(long userSenderId, long userRecipientId) {
        User userSender = new User();
        userSender.setUserId(userSenderId);
        User userRecipient = new User();
        userRecipient.setUserId(userRecipientId);

        Follow follow = followRepository.findByUserSenderAndUserRecipient(userSender, userRecipient);
        if (follow!=null){
            delete(follow.getFollowId());
        } else {
            boolean value = userRepository.checkFollowStatus(userRecipientId);
            Follow follow1 = new Follow();
            follow1.setUserRecipient(userRecipient);
            follow1.setUserSender(userSender);
            follow1.setStatus(value);
            save(follow1);
        }
    }

    @Override
    public void save(Follow follow) {
        followRepository.save(follow);
    }

    @Override
    public void delete(long followId) {
        followRepository.deleteById(followId);
    }

    @Override
    public List<FollowInfo> requestList(long userId) {
        List<FollowInfo> followInfoList = new ArrayList<FollowInfo>();

        List<Follow> followList = followRepository.requestListFollow(userId);
        for(Follow follow:followList){
            FollowInfo followInfo = new FollowInfo();
            followInfo.setFollowId(follow.getFollowId());
            followInfo.setStatus(false);
            followInfo.setUserId(follow.getUserSender().getUserId());
            followInfo.setUserName(follow.getUserSender().getName());
            followInfo.setUserImage(follow.getUserSender().getAvatar());

            followInfoList.add(followInfo);
        }

        return followInfoList;
    }

    @Override
    public List<FollowInfo> followerList(long userId) {
        User user = new User();
        user.setUserId(userId);
        List<FollowInfo> followInfoList = new ArrayList<FollowInfo>();
        List<Follow> list = followRepository.findByUserRecipientAndStatus(user, true);
        for(Follow follow:list){
            FollowInfo followInfo = new FollowInfo();
            followInfo.setFollowId(follow.getFollowId());
            followInfo.setStatus(follow.isStatus());
            followInfo.setUserId(follow.getUserSender().getUserId());
            followInfo.setUserName(follow.getUserSender().getName());
            followInfo.setUserImage(follow.getUserSender().getAvatar());

            followInfoList.add(followInfo);
        }

        return followInfoList;
    }

    @Override
    public List<FollowInfo> followingList(long userId) {
        User user = new User();
        user.setUserId(userId);
        List<FollowInfo> followInfoList = new ArrayList<FollowInfo>();
        List<Follow> list = followRepository.findByUserSenderAndStatus(user, true);
        for(Follow follow:list){
            FollowInfo followInfo = new FollowInfo();
            followInfo.setFollowId(follow.getFollowId());
            followInfo.setStatus(follow.isStatus());
            followInfo.setUserId(follow.getUserSender().getUserId());
            followInfo.setUserName(follow.getUserSender().getName());
            followInfo.setUserImage(follow.getUserSender().getAvatar());

            followInfoList.add(followInfo);
        }

        return followInfoList;
    }
}
