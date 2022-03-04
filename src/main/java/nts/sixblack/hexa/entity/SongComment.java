package nts.sixblack.hexa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "songComment")
public class SongComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long songCommentId;
    @Column(columnDefinition = "ntext")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    @ManyToOne
    @JoinColumn(name = "songId")
    Song song;
}
