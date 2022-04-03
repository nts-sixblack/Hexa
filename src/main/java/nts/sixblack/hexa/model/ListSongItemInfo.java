package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListSongItemInfo {
    private long listSongItemId;
    private SongInfo song;
//    private Date dateCreate;
    private String dateCreate;

}
