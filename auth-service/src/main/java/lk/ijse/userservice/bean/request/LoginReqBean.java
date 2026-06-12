package lk.ijse.userservice.bean.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Title: mechani-link
 * Description: LoginReqBean Class
 * Created by Abhishek Ashinsa on 11/27/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginReqBean {
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
