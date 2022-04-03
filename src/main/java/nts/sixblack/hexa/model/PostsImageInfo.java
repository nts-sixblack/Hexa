package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsImageInfo {
    private long postsImageId;
    private String image;
//    private Date dateCreate;
    private String dateCreate;
}
