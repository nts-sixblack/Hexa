package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongInfo {
    private long songId;
    private String name;
    private String image;
    private String link;
    private List<SongUserInfo> songUserList;
    private List<SongFeelInfo> songFeelList;
    private List<SongCommentInfo> songCommentList;

}
