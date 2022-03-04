package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChannelInfo {
    private long userChannelId;
    private UserInfo user;
    private ChannelInfo channel;
}
