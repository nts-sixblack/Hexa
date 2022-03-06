package nts.sixblack.hexa.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongForm {
    private String name;
    private MultipartFile image;
    private MultipartFile song;
    private long songCategoryId;
    private List<Long> songUser;

}
