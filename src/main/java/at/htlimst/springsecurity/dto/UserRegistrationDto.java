package at.htlimst.springsecurity.dto;


import at.htlimst.springsecurity.config.constraint.FieldMatch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "Passwörter müssen übereinstimmen"),
})
public class UserRegistrationDto {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 8, message = "muss mehr als 8 Zeichen lang sein")
    private String password;

    @NotEmpty
    @Size(min = 8, message = "muss mehr als 8 Zeichen lang sein")
    private String confirmPassword;

}
