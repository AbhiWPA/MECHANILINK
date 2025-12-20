package lk.ijse.userservice.service.impl;

import jakarta.validation.constraints.NotNull;
import lk.ijse.userservice.bean.ResponseBean;
import lk.ijse.userservice.bean.request.LoginReqBean;
import lk.ijse.userservice.bean.request.SignUpReqBean;
import lk.ijse.userservice.bean.response.LoginResBean;
import lk.ijse.userservice.constant.AppConstant;
import lk.ijse.userservice.exception.NoDataFoundException;
import lk.ijse.userservice.persistence.MechanicRepo;
import lk.ijse.userservice.persistence.MerchantRepo;
import lk.ijse.userservice.persistence.UserRepo;
import lk.ijse.userservice.persistence.entity.MechanicEntity;
import lk.ijse.userservice.persistence.entity.MerchantEntity;
import lk.ijse.userservice.persistence.entity.UserEntity;
import lk.ijse.userservice.service.AuthService;
import lk.ijse.userservice.service.JwtService;
import lk.ijse.userservice.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;

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
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MechanicRepo mechanicRepo;
    private final MerchantRepo merchantRepo;
    private final ObjectMapper objectMapper;

    private static final String uuID = "000000";

    @Override
    public ResponseEntity<ResponseBean> signUp(SignUpReqBean signUpReqBean) {

        if (signUpReqBean != null) {
            if (signUpReqBean.getRole().equals(Role.DRIVER)) {
                log.debug("Sign up as a driver");
                return driverSignUp(signUpReqBean);
            } else if (signUpReqBean.getRole().equals(Role.MECHANIC)) {
                log.debug("Sign up as a mechanic");
                return mechanicSignUp(signUpReqBean);
            } else if (signUpReqBean.getRole().equals(Role.MERCHANT)) {
                log.debug("Sign up as a merchant");
                return merchantSignUp(signUpReqBean);
            }
            return ResponseEntity.ok(new ResponseBean(AppConstant.UNAUTHORIZED, "Unauthorized access. Please Select the Role!", null));
        }
        return ResponseEntity.ok(new ResponseBean(AppConstant.NOT_FOUND, "Signup data not found,Please try again", null));
    }


    private ResponseEntity<ResponseBean> mechanicSignUp(SignUpReqBean signUpReqBean) {
        log.debug("Mechanic registration");
        if(signUpReqBean.getMechanic() != null){
            log.debug("Mechanic registration :" + signUpReqBean.getMechanic());

            String userID = UserIDGenerator.generateUserId(signUpReqBean.getRole());

            boolean isExist = userRepo.existsByEmailOrId(signUpReqBean.getEmail(), userID);

            if(isExist){
                log.debug("Mechanic registration : User Already Exists" + signUpReqBean.getEmail());
                return ResponseEntity.ok(ResponseBean.exists(signUpReqBean.getEmail()));
            }

            UserEntity user = UserEntity.builder()
                    .id(userID)
                    .username(signUpReqBean.getUsername())
                    .email(signUpReqBean.getEmail())
                    .password(passwordEncoder.encode(signUpReqBean.getPassword()))
                    .role(Role.valueOf(signUpReqBean.getRole().name()))
                    .firstName(signUpReqBean.getFirstName())
                    .lastName(signUpReqBean.getLastName())
                    .phoneNumber(signUpReqBean.getPhoneNumber())
                    .active(true)
                    .mechanic(MechanicEntity.builder()
                            .id(userID)
                            .specialization(signUpReqBean.getMechanic().getSpecialization())
                            .yearsOfExperience(signUpReqBean.getMechanic().getYearsOfExperience())
                            .hourlyRate(signUpReqBean.getMechanic().getHourlyRate())
                            .isCertified(signUpReqBean.getMechanic().getIsCertified())
                            .availableTimeSlots(signUpReqBean.getMechanic().getAvailableTimeSlots())
                            .serviceRadiusKm(signUpReqBean.getMechanic().getServiceRadiusKm())
                            .toolsAvailable(signUpReqBean.getMechanic().getToolsAvailable())
                            .vehicleTypesSpecialized(signUpReqBean.getMechanic().getVehicleTypesSpecialized())
                            .skills(signUpReqBean.getMechanic().getSkills())
                            .certifications(signUpReqBean.getMechanic().getCertifications())
                            .status(StatusConstant.ACTIVE)
                            .build())
                    .build();

            log.debug("User ::::::::::::: " + user.toString());
            String token = jwtService.generateToken(user);
            user.setJwtToken(token);
            userRepo.save(user);
            log.debug("Token ::::::::::::: " + token);
            return ResponseEntity.ok(new ResponseBean(AppConstant.SUCCESS, signUpReqBean.getUsername(), token));


        }
        return ResponseEntity.ok(new ResponseBean(AppConstant.UNAUTHORIZED, null, "Sign up details wrong please try again!"));
    }


    private ResponseEntity<ResponseBean> merchantSignUp(SignUpReqBean signUpReqBean) {
        log.debug("Merchant registration");

        if(signUpReqBean.getMerchant() != null){
            log.debug("Merchant registration :" + signUpReqBean.getMechanic());

            String userID = UserIDGenerator.generateUserId(signUpReqBean.getRole());

            boolean isExist = userRepo.existsByEmailOrId(signUpReqBean.getEmail(), userID);

            if(isExist){
                log.debug("Merchant registration : User Already Exists" + signUpReqBean.getEmail());
                return ResponseEntity.ok(ResponseBean.exists(signUpReqBean.getEmail()));
            }

            UserEntity user = UserEntity.builder()
                    .id(userID)
                    .username(signUpReqBean.getUsername())
                    .email(signUpReqBean.getEmail())
                    .password(passwordEncoder.encode(signUpReqBean.getPassword()))
                    .role(Role.valueOf(signUpReqBean.getRole().name()))
                    .firstName(signUpReqBean.getFirstName())
                    .lastName(signUpReqBean.getLastName())
                    .phoneNumber(signUpReqBean.getPhoneNumber())
                    .active(true)
                    .merchant(MerchantEntity.builder()
                            .id(userID)
                            .businessName(signUpReqBean.getMerchant().getBusinessName())
                            .businessRegistrationNumber(signUpReqBean.getMerchant().getBusinessRegistrationNumber())
                            .businessType(signUpReqBean.getMerchant().getBusinessType())
                            .businessAddress(signUpReqBean.getMerchant().getBusinessAddress())
                            .businessPhone(signUpReqBean.getMerchant().getBusinessPhone())
                            .businessEmail(signUpReqBean.getMerchant().getBusinessEmail())
                            .businessWebsite(signUpReqBean.getMerchant().getBusinessWebsite())
                            .businessDescription(signUpReqBean.getMerchant().getBusinessDescription())
                            .businessHours(signUpReqBean.getMerchant().getBusinessHours())
                            .deliveryAvailable(signUpReqBean.getMerchant().getDeliveryAvailable())
                            .paymentMethodsAccepted(signUpReqBean.getMerchant().getPaymentMethodsAccepted())
                            .productCategories(signUpReqBean.getMerchant().getProductCategories())
                            .mainProductsServices(signUpReqBean.getMerchant().getMainProductsServices())
                            .brandsAvailable(signUpReqBean.getMerchant().getBrandsAvailable())
                            .status(StatusConstant.ACTIVE)
                            .build())
                    .build();

            log.debug("User ::::::::::::: " + user.toString());
            String token = jwtService.generateToken(user);
            user.setJwtToken(token);
            userRepo.save(user);
            log.debug("Token ::::::::::::: " + token);
            return ResponseEntity.ok(new ResponseBean(AppConstant.SUCCESS, signUpReqBean.getUsername(), token));

        }
        return ResponseEntity.ok(new ResponseBean(AppConstant.UNAUTHORIZED, null, "Sign up details wrong please try again!"));
    }

    private ResponseEntity<ResponseBean> driverSignUp(SignUpReqBean signUpReqBean) {
        log.debug("Driver registration");

        String userID = UserIDGenerator.generateUserId(signUpReqBean.getRole());

        boolean isExist = userRepo.existsByEmailOrId(signUpReqBean.getEmail(), userID);

        if(isExist){
            log.debug("Merchant registration : User Already Exists" + signUpReqBean.getEmail());
            return ResponseEntity.ok(ResponseBean.exists(signUpReqBean.getEmail()));
        }

        UserEntity user = UserEntity.builder()
                .id(userID)
                .username(signUpReqBean.getUsername())
                .email(signUpReqBean.getEmail())
                .password(passwordEncoder.encode(signUpReqBean.getPassword()))
                .role(Role.valueOf(signUpReqBean.getRole().name()))
                .firstName(signUpReqBean.getFirstName())
                .lastName(signUpReqBean.getLastName())
                .phoneNumber(signUpReqBean.getPhoneNumber())
                .active(true)
                .build();
        log.debug("User ::::::::::::: " + user.toString());
        String token = jwtService.generateToken(user);
        user.setJwtToken(token);
        userRepo.save(user);
        log.debug("Token ::::::::::::: " + token);
        return ResponseEntity.ok(new ResponseBean(AppConstant.SUCCESS, signUpReqBean.getUsername(), token));
    }

    @Override
    public ResponseEntity<ResponseBean> login(LoginReqBean bean) {
        log.debug("Starting to find the User");
        UserEntity user = userRepo.findByEmail(bean.getEmail()).orElseThrow(() ->
                new NoDataFoundException(ResponseMessageConstant.USER_NOT_FOUND)
        );

        log.debug("Starting to Authenticate the User: " + user);
        return isAuthenticated(bean, user);

    }

    private ResponseEntity<ResponseBean> isAuthenticated(@NotNull LoginReqBean loginBean, @NotNull UserEntity loginEntity) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginEntity.getEmail(),
                        loginBean.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            log.info("User Login Success");
            String accessToken = jwtService.generateToken(loginEntity);

            return saveUserAccessToken(loginEntity, accessToken)
                    ? ResponseEntity.ok(ResponseBean.success(new LoginResBean(
                            loginEntity.getUsername(),
                            loginEntity,
//                            getUserDetails(loginEntity.getId(), loginEntity.getRole().name()),
                            accessToken
                    )
            )) : ResponseEntity.ok(ResponseBean.unauthorized("Failed to save the Access Token"));
        } else {
            return ResponseEntity.ok(ResponseBean.unauthorized("Invalid Credentials"));
        }
    }

//    private Object getUserDetails(@NotNull String id, String role) {
////        log.debug("Getting user details : {}", id, role );
//
//        if(role.equals(Role.MECHANIC.name())) {
//            return mechanicRepo.findById(id).get().toString();
//        } else if (role.equals(Role.MERCHANT.name())) {
//            return merchantRepo.findById(id).get();
//        }
//        return null;
//    }

    private boolean saveUserAccessToken(@NotNull UserEntity loginEntity, String accessToken) {
        loginEntity.setJwtToken(accessToken);
        userRepo.save(loginEntity);
        return true;

    }
}
