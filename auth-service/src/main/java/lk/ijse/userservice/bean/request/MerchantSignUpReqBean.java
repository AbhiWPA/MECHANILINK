package lk.ijse.userservice.bean.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Title: mechani-link
 * Description: MerchantSignUpReqBean Class
 * Created by Abhishek Ashinsa on 11/23/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantSignUpReqBean {

    @NotBlank(message = "Business name is required")
    private String businessName;

    private String businessRegistrationNumber;
    private String businessType;
    private String businessAddress;
    private String businessPhone;
    private String businessEmail;
    private String businessWebsite;
    private String businessDescription;
    private String businessHours;

    // Business Details
//    private Integer establishedYear;
//    private Integer numberOfEmployees;
//    private Double annualRevenue;
//    private String businessSize;
    private Boolean deliveryAvailable;
//    private Boolean warrantyProvided;
//    private String returnPolicy;
    private String paymentMethodsAccepted;

    // Product Information
    private List<String> productCategories;
    private String mainProductsServices;
    private String brandsAvailable;
//    private String inventorySize;

    // Legal & Financial
//    private String taxIdentificationNumber;
//    private String tradeLicenseNumber;
//    private LocalDateTime licenseExpiryDate;
//    private String bankName;
//    private String accountNumber;
//    private String accountHolderName;
//    private String bankBranch;
}
