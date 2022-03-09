package nts.sixblack.hexa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Column(columnDefinition = "ntext")
    private String name;
    @Column(columnDefinition = "varchar(255)")
    private String image;
    @Column(columnDefinition = "varchar(255)")
    private String song;

    @ManyToOne
    @JoinColumn(name = "songCategoryId")
    SongCategory songCategory;

    @OneToMany(mappedBy = "song")
    List<SongUser> songUserList;

    @OneToMany(mappedBy = "song")
    List<SongFeel> songFeelList;

    @OneToMany(mappedBy = "song")
    List<SongComment> songCommentList;
}
