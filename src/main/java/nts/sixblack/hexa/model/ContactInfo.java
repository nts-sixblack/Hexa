package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo {
    private long contactId;
    private UserInfo user1;
    private UserInfo user2;
    private ChannelInfo channel;
}
