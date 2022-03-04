package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsCommentInfo {
    private long postsCommentId;
    private String comment;
    private UserInfo user;
    private PostsInfo posts;
}
