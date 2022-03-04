package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelInfo {
    private long channelId;
    private String name;
    private String decription;
    private List<UserChannelInfo> userChannelList;
    private List<MessageInfo> messageList;
}
