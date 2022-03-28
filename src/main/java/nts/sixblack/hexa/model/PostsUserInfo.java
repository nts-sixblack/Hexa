package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsUserInfo {
    private long postsUserId;
    private long userId;
    private String name;
    private String image;
    private Date dateCreate;

}
