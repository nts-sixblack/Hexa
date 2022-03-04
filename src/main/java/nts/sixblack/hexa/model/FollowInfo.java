package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowInfo {
    private long followId;
    private int status;
    private UserInfo userSender;
    private UserInfo userRecipient;
}
