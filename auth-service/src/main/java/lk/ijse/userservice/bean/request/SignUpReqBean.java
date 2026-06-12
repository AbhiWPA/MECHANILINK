package lk.ijse.userservice.bean.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lk.ijse.userservice.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Title: mechani-link
 * Description: SignUpReqBean Class
 * Created by Abhishek Ashinsa on 11/22/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpReqBean {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    private String nic;
    private String address;
    private Double latitude;
    private Double longitude;

    @NotNull(message = "Role is required")
    private Role role;

    @Valid
    private MechanicSignUpReqBean mechanic;

    @Valid
    private MerchantSignUpReqBean merchant;
}
