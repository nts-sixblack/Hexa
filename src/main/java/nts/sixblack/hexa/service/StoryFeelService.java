package nts.sixblack.hexa.service;

import nts.sixblack.hexa.entity.StoryFeel;
import nts.sixblack.hexa.form.Like;

public interface StoryFeelService {

    void delete(long storyId);

    StoryFeel save(StoryFeel storyFeel);

    long checkFeel(long storyId, long userId);

    void like(Like like);
}
