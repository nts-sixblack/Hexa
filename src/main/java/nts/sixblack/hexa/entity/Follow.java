package nts.sixblack.hexa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "follow")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long followId;
    private int status;

    @ManyToOne
    @JoinColumn(name = "userSender")
    User userSender;

    @ManyToOne
    @JoinColumn(name = "userRecipient")
    User userRecipient;

}
