package appnewssite.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotNull(message = "username bo'sh bo'lmasin")
    private String username;

    @NotNull(message = "password bo'sh bo'lmasin")
    private String password;

}
