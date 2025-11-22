package lk.ijse.userservice.bean.response;

import lk.ijse.userservice.util.Role;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Title: mechani-link
 * Description: SignUpResBean Class
 * Created by Abhishek Ashinsa on 11/23/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */

@Data
public class SignUpResBean {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean active;
    private LocalDateTime createdAt;
}
