package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongCommentInfo {
    private long songCommentId;
    private String comment;
    private UserInfo user;
    private SongInfo song;
}
