package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongCommentInfo {
    private long songCommentId;
    private String comment;
    private long userId;
    private String name;
    private String image;
    private Date dateCreate;

}
