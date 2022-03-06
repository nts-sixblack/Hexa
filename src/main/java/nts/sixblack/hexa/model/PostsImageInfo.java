package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsImageInfo {
    private long postsImageId;
    private MultipartFile file;
    private String image;
    private PostsInfo posts;
}
