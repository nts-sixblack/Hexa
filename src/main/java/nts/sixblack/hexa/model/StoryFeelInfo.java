package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryFeelInfo {
    private long storyFeelId;
    private boolean feel;
    private long storyId;
    private long userId;
    //    private Date dateCreate;
    private String dateCreate;
}
