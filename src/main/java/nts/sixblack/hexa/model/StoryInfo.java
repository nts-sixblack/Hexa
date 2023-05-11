package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryInfo {
    private long storyId;
    private String link;
    private String type;
    private long totalFeel;
    private long totalComment;
    //    private Date dateCreate;
    private String dateCreate;
    private boolean feel;
    private UserInfo userInfo;

    private List<StoryCommentInfo> storyCommentInfoList;
    private List<StoryFeelInfo> storyFeelInfoList;
}
