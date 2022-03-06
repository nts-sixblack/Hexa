package nts.sixblack.hexa.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsForm {
    private String caption;
//    private List<Long> user;
    private MultipartFile files;
}
