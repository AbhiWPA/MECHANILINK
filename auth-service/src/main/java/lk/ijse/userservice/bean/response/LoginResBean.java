package lk.ijse.userservice.bean.response;

import lk.ijse.userservice.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class LoginResBean {
    private String username;
    private UserEntity user;
//    private Object object;
    private String token;

}
