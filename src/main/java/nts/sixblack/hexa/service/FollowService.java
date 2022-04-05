package nts.sixblack.hexa.service;

import nts.sixblack.hexa.entity.Follow;
import nts.sixblack.hexa.model.FollowInfo;

import java.util.List;

public interface FollowService {
    void sendRequestFollow(long userSenderId, long userRecipientId);
    void save(Follow follow);
    void delete(long followId);
    List<FollowInfo> requestList(long userId);
    List<FollowInfo> followerList(long userId);
    List<FollowInfo> followingList(long userId);
    List<Long> listUserRecipient(long userId);
    Follow findFollow(long myUserId, long userId);
}
