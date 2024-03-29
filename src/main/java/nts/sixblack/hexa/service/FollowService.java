package nts.sixblack.hexa.service;

import nts.sixblack.hexa.entity.Follow;
import nts.sixblack.hexa.model.FollowInfo;

import java.util.List;

public interface FollowService {
    void sendRequestFollow(long userSenderId, long userRecipientId);
    void save(Follow follow);
    void accept(long followId);
    void delete(long followId);
    List<FollowInfo> requestList(long userId);
    List<FollowInfo> requestList(long userId, int page);
    List<FollowInfo> followerList(long userId);
    List<FollowInfo> followerList(long userId, int page);
    List<FollowInfo> followingList(long userId);
    List<FollowInfo> followingList(long userId, int page);
    List<Long> listUserRecipient(long userId);
    List<Follow> listuserRecipient(long userId);
    Follow findFollow(long myUserId, long userId);
    int numberFollower(long userId);
    int numberFollowing(long userId);
}
