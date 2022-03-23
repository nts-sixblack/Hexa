package nts.sixblack.hexa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "listSongItem")
public class ListSongItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long listSongItemId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @ManyToOne
    @JoinColumn(name = "songId")
    Song song;

    @ManyToOne
    @JoinColumn(name = "listSongId")
    ListSong listSong;
}
