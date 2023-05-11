package nts.sixblack.hexa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "storyComment")
public class StoryComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long storyFeelId;
    private String comment;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @ManyToOne
    @JoinColumn(name = "storyId")
    Story story;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;
}
