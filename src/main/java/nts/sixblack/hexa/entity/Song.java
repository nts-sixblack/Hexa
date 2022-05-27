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
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long songId;
//    @Column(columnDefinition = "ntext")
    private String name;
//    @Column(columnDefinition = "varchar(255)")
    private String image;
//    @Column(columnDefinition = "varchar(255)")
    private String song;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @ManyToOne
    @JoinColumn(name = "songCategoryId")
    SongCategory songCategory;

    @OneToMany(mappedBy = "song")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<SongUser> songUserList;

    @OneToMany(mappedBy = "song")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<SongFeel> songFeelList;

    @OneToMany(mappedBy = "song")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<SongComment> songCommentList;

    @OneToMany(mappedBy = "song")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<ListSongItem> listSongItemList;
}
