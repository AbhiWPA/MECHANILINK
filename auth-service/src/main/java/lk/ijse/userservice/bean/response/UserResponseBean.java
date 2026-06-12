package lk.ijse.userservice.bean.response;

import lk.ijse.userservice.util.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Title: mechani-link
 * Description: UserResponseBean Class
 * Created by Abhishek Ashinsa on 6/13/2026
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseBean {
    private String id;
    private String username;
    private String email;
    private Role role;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Boolean active;
    private Object roleSpecificData;

}
