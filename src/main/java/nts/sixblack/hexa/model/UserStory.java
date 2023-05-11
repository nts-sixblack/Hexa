package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStory {
    private long userId;
    private String image;
    private String name;
    private List<StoryInfo> storyInfoList = new ArrayList<StoryInfo>();
}
