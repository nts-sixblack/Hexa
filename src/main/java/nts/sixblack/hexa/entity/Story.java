package nts.sixblack.hexa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "story")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long storyId;
    private String type;
    private String link;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    @OneToMany(mappedBy = "story")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<StoryFeel> storyFeelList;

    @OneToMany(mappedBy = "story")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<StoryComment> storyCommentList;

}
