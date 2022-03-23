package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageInfo {
    private long messageId;
    private String message;
    private long userId;
    private String image;
    private String name;
    private long channelId;
    private String dateCreate;

}
