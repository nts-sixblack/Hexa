package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo {
    private long contactId;
    private long userId1;
    private long userId2;
    private long channelId;
    private String dateCreate;

}
