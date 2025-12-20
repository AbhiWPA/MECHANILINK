package lk.ijse.userservice.service;

import lk.ijse.userservice.bean.ResponseBean;
import lk.ijse.userservice.bean.request.LoginReqBean;
import lk.ijse.userservice.bean.request.SignUpReqBean;
import org.springframework.http.ResponseEntity;

/**
 * Title: mechani-link
 * Description: AuthService Class
 * Created by Abhishek Ashinsa on 11/23/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */
    
public interface AuthService {

    ResponseEntity<ResponseBean> signUp(SignUpReqBean signUpReqBean);

    ResponseEntity<ResponseBean> login(LoginReqBean bean);
}
