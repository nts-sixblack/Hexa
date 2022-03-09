package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongFeelInfo {
    private long songFeelId;
    private boolean feel;
    private long userId;
    private String name;
    private String image;
}
