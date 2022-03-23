package nts.sixblack.hexa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "listSong")
public class ListSong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long listSongId;
    private String name;
    private boolean status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @OneToMany(mappedBy = "listSong")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<ListSongItem> listSongItemList;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;
}
