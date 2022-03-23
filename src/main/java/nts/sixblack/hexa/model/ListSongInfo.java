package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListSongInfo {
    private long listSongId;
    private long userId;
    private String nameOfList;
    private String name;
    private String image;
    private String dateCreate;
    private List<ListSongItemInfo> listSongItemList;
}
