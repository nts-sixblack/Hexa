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
@Table(name = "songCategory")
public class SongCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long songCategoryId;
//    @Column(columnDefinition = "ntext")
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @OneToMany(mappedBy = "songCategory")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<Song> songList;
}
