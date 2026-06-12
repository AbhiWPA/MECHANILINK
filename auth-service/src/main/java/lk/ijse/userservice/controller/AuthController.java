package lk.ijse.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lk.ijse.userservice.bean.ResponseBean;
import lk.ijse.userservice.bean.request.LoginReqBean;
import lk.ijse.userservice.bean.request.SignUpReqBean;
import lk.ijse.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
@Tag(name = "Authentication", description = "Authentication endpoints")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    @Operation(summary = "Register a new user", description = "Creates a new user account with role-specific details")
    public ResponseEntity<ResponseBean> userSignUp(@RequestBody SignUpReqBean bean) {
        log.debug("User Sign up request received!");
        if (bean != null) {
            return authService.signUp(bean);
        }
        log.error("User Controller | User Sign Up - Sign up bean is null");
        return ResponseEntity.ok(ResponseBean.notfound("Cannot Sign up, Please check the credentials...!"));
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate user", description = "Login with email and password to receive JWT token")
    public ResponseEntity<ResponseBean> userLogin(@RequestBody LoginReqBean bean) {
        log.debug("User login request received!");
        if (bean != null) {
            return authService.login(bean);
        }
        log.error("User Controller | User login - Sign up bean is null");
        return ResponseEntity.ok(ResponseBean.notfound("Cannot Sign up, Please check the credentials...!"));
    }
}
