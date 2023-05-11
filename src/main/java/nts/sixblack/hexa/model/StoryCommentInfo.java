package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryCommentInfo {
    private long storyCommentId;
    private String comment;
    private long storyId;
    private long userId;
    private String name;
    private String image;
    //    private Date dateCreate;
    private String dateCreate;
}
