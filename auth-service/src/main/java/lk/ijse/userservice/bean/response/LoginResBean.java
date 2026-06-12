package lk.ijse.userservice.bean.response;

import lk.ijse.userservice.persistence.entity.UserEntity;
import lk.ijse.userservice.util.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Title: mechani-link
 * Description: LoginResBean Class
 * Created by Abhishek Ashinsa on 11/27/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResBean {
    private String accessToken;
    private String tokenType = "Bearer";
    private String userId;
    private String email;
    private String username;
    private Role role;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Object roleSpecificData;

}
