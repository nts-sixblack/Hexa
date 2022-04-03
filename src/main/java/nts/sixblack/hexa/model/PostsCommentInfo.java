package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsCommentInfo {
    private long postsCommentId;
    private String comment;
    private long postsId;
    private long userId;
    private String name;
    private String image;
//    private Date dateCreate;
    private String dateCreate;


}
