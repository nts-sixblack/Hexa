package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsFeelInfo {
    private long postsFeelId;
    private boolean feel;
    private long postsId;
    private Date dateCreate;

}
