package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsUserInfo {
    private long postsUserId;
    private PostsInfo posts;
    private UserInfo user;
}
