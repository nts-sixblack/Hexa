package nts.sixblack.hexa.service;

import nts.sixblack.hexa.entity.Follow;
import nts.sixblack.hexa.entity.User;

import java.util.List;

public interface FollowService {
    public void sendFollow(long userSender, long userRecipient);
    public List<Follow> requestList(long userId);
    public void accessFollow(long followId);
    public void followStatus(long userId);
}
