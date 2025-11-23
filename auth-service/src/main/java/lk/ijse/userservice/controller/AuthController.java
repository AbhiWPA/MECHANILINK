package lk.ijse.userservice.controller;

import lk.ijse.userservice.bean.ResponseBean;
import lk.ijse.userservice.bean.request.SignUpReqBean;
import lk.ijse.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: mechani-link
 * Description: UserController Class
 * Created by Abhishek Ashinsa on 11/22/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<ResponseBean> userSignUp(@RequestBody SignUpReqBean bean) {
        log.debug("User Sign up request received!");
        if (bean != null) {
            return authService.signUp(bean);
        }
        log.error("User Controller | User Sign Up - Sign up bean is null");
        return ResponseEntity.ok(ResponseBean.notfound("Cannot Sign up, Please check the credentials...!"));
    }

}
