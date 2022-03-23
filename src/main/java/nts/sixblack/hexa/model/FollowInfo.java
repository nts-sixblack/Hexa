package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowInfo {
    private long followId;
    private boolean status;
    private long userId;
    private String userName;
    private String userImage;
    private String dateCreate;

}
