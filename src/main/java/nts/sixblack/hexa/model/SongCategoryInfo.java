package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongCategoryInfo {
    private long songCategoryId;
    private String name;
    private String image;

    private List<SongInfo> songList;
}
