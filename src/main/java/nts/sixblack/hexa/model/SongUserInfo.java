package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongUserInfo {
    private long songUserId;
    private long userId;
    private String userName;
    private String image;
}
