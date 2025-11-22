package lk.ijse.userservice.service.impl;

import lk.ijse.userservice.bean.ResponseBean;
import lk.ijse.userservice.bean.request.SignUpReqBean;
import lk.ijse.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Title: mechani-link
 * Description: AuthServiceImpl Class
 * Created by Abhishek Ashinsa on 11/23/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public ResponseEntity<ResponseBean> signUp(SignUpReqBean signUpReqBean) {
        return null;
    }
}
