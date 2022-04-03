package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsInfo {
    private long postsId;
    private String caption;
    private long totalFeel;
    private long totalComment;
//    private Date dateCreate;
    private String dateCreate;

    private List<PostsUserInfo> postsUserList;
    private List<PostsCommentInfo> postsCommentList;
    private List<PostsFeelInfo> postsFeelList;
    private List<PostsImageInfo> postsImageList;
}
