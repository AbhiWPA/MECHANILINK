package lk.ijse.userservice.service.impl;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lk.ijse.userservice.bean.request.LoginReqBean;
import lk.ijse.userservice.bean.request.MechanicSignUpReqBean;
import lk.ijse.userservice.bean.request.MerchantSignUpReqBean;
import lk.ijse.userservice.bean.request.SignUpReqBean;
import lk.ijse.userservice.bean.response.LoginResBean;
import lk.ijse.userservice.bean.response.UserResponseBean;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

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

    private final UserRepo userRepository;
    private final MechanicRepo mechanicRepository;
    private final MerchantRepo merchantRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public ResponseEntity signUp(SignUpReqBean request) {
        log.info("Registering new user with email: {}", request.getEmail());

        // Validate email uniqueness
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered: " + request.getEmail());
        }

        // Validate username uniqueness
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already taken: " + request.getUsername());
        }

        // Validate role-specific data
        validateRoleSpecificData(request);

        // Generate user ID
        String userId = generateUserId(request.getRole());

        // Create base user
        UserEntity user = createBaseUser(request, userId);
        UserEntity savedUser = userRepository.save(user);

        // Create role-specific entity
        Object roleSpecificData = createRoleSpecificEntity(savedUser, request);

        log.info("User registered successfully with ID: {}", savedUser.getId());

        return ResponseEntity.ok(
                UserResponseBean.builder()
                        .id(savedUser.getId())
                        .username(savedUser.getUsername())
                        .email(savedUser.getEmail())
                        .role(savedUser.getRole())
                        .firstName(savedUser.getFirstName())
                        .lastName(savedUser.getLastName())
                        .phoneNumber(savedUser.getPhoneNumber())
                        .active(savedUser.getActive())
                        .roleSpecificData(roleSpecificData)
                        .build()
        );
    }

    @Override
    public ResponseEntity login(LoginReqBean request) {
        log.info("Processing login for user: {}", request.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);

        // Get role-specific data for response
        Object roleSpecificData = getRoleSpecificData(user);

        return ResponseEntity.ok(
                LoginResBean.builder()
                        .accessToken(token)
                        .tokenType("Bearer")
                        .userId(user.getId())
                        .email(user.getEmail())
                        .username(user.getUsername())
                        .role(user.getRole())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .phoneNumber(user.getPhoneNumber())
                        .roleSpecificData(roleSpecificData)
                        .build()
        );
    }

    private void validateRoleSpecificData(SignUpReqBean request) {
        if (request.getRole() == Role.MECHANIC && request.getMechanic() == null) {
            throw new RuntimeException("Mechanic details are required for MECHANIC role");
        }
        if (request.getRole() == Role.MERCHANT && request.getMerchant() == null) {
            throw new RuntimeException("Merchant details are required for MERCHANT role");
        }
    }

    private UserEntity createBaseUser(SignUpReqBean request, String userId) {
        return UserEntity.builder()
                .id(userId)
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .nic(request.getNic())
                .address(request.getAddress())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .active(true)
                .isVerified(false)
                .createdAt(LocalDateTime.now())
                .build();
    }

    private Object createRoleSpecificEntity(UserEntity user, SignUpReqBean request) {
        if (request.getRole() == Role.MECHANIC && request.getMechanic() != null) {
            return createMechanic(user, request.getMechanic());
        } else if (request.getRole() == Role.MERCHANT && request.getMerchant() != null) {
            return createMerchant(user, request.getMerchant());
        }
        return null;
    }

    private MechanicEntity createMechanic(UserEntity user, @Valid MechanicSignUpReqBean mechanicReq) {
        MechanicEntity mechanic = MechanicEntity.builder()
                .id(user.getId())
                .user(user)
                .specialization(mechanicReq.getSpecialization())
                .yearsOfExperience(mechanicReq.getYearsOfExperience())
                .hourlyRate(mechanicReq.getHourlyRate())
                .isCertified(mechanicReq.getIsCertified())
                .availableTimeSlots(mechanicReq.getAvailableTimeSlots())
                .serviceRadiusKm(mechanicReq.getServiceRadiusKm())
                .toolsAvailable(mechanicReq.getToolsAvailable())
                .vehicleTypesSpecialized(mechanicReq.getVehicleTypesSpecialized())
                .skills(mechanicReq.getSkills() != null ? mechanicReq.getSkills() : new ArrayList<>())
                .certifications(mechanicReq.getCertifications() != null ? mechanicReq.getCertifications() : new ArrayList<>())
                .status(StatusConstant.ACTIVE)
                .build();

        user.setMechanic(mechanic);
        return mechanicRepository.save(mechanic);
    }

    private MerchantEntity createMerchant(UserEntity user, @Valid MerchantSignUpReqBean merchantReq) {
        MerchantEntity merchant = MerchantEntity.builder()
                .id(user.getId())
                .user(user)
                .businessName(merchantReq.getBusinessName())
                .businessRegistrationNumber(merchantReq.getBusinessRegistrationNumber())
                .businessType(merchantReq.getBusinessType())
                .businessAddress(merchantReq.getBusinessAddress())
                .businessPhone(merchantReq.getBusinessPhone())
                .businessEmail(merchantReq.getBusinessEmail())
                .businessWebsite(merchantReq.getBusinessWebsite())
                .businessDescription(merchantReq.getBusinessDescription())
                .businessHours(merchantReq.getBusinessHours())
                .deliveryAvailable(merchantReq.getDeliveryAvailable())
                .paymentMethodsAccepted(merchantReq.getPaymentMethodsAccepted())
                .productCategories(merchantReq.getProductCategories() != null ? merchantReq.getProductCategories() : new ArrayList<>())
                .mainProductsServices(merchantReq.getMainProductsServices())
                .brandsAvailable(merchantReq.getBrandsAvailable())
                .status(StatusConstant.ACTIVE)
                .build();

        user.setMerchant(merchant);
        return merchantRepository.save(merchant);
    }

    private Object getRoleSpecificData(UserEntity user) {
        if (user.getRole() == Role.MECHANIC && user.getMechanic() != null) {
            return user.getMechanic();
        } else if (user.getRole() == Role.MERCHANT && user.getMerchant() != null) {
            return user.getMerchant();
        }
        return null;
    }

    private String generateUserId(Role role) {
        String prefix = switch (role) {
            case ADMIN -> "ADMIN";
            case MECHANIC -> "MECH";
            case DRIVER -> "DRIV";
            case MERCHANT -> "MERCH";
        };
        String uuid = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return prefix + "_" + uuid;
    }
}
