package nts.sixblack.hexa.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private long userId;
    private String firstName;
    private String lastName;
    private String phone;
    private boolean followStatus;
}
