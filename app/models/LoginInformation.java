package models;

import org.hibernate.validator.constraints.NotBlank;
import play.data.validation.Constraints;

/**
 * Created by An on 5/14/2017.
 */
public class LoginInformation {
    @NotBlank
    //@Constraints.Email(message = "Wrong email format!")
    public String username;
    @NotBlank
    @Constraints.MinLength(5)
    public String password;
}
