package nts.sixblack.hexa.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterForm {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
}
