package lk.ijse.userservice.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lk.ijse.userservice.util.StatusConstant;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Title: mechani-link
 * Description: MerchantEntity Class
 * Created by Abhishek Ashinsa on 11/25/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */

@Entity
@Table(name = "merchants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "user")
public class MerchantEntity {

    @Id
    @Column(name = "id", length = 50)
    private String id;

    @OneToOne
    @JoinColumn(name = "id")
    @JsonIgnoreProperties({"mechanic", "merchant"})
    private UserEntity user;

    @Column(name = "business_name", nullable = false)
    private String businessName;

    @Column(name = "business_registration_number", unique = true)
    private String businessRegistrationNumber;

    private String businessType;
    private String businessAddress;
    private String businessPhone;
    private String businessEmail;
    private String businessWebsite;
    private String businessDescription;
    private String businessHours;
    private Boolean deliveryAvailable;
    private String paymentMethodsAccepted;

    @ElementCollection
    @CollectionTable(name = "merchant_categories", joinColumns = @JoinColumn(name = "merchant_id"))
    @Column(name = "category")
    private List<String> productCategories = new ArrayList<>();

    private String mainProductsServices;
    private String brandsAvailable;

    @Enumerated(EnumType.STRING)
    @Column(name = "merchant_status")
    private StatusConstant status;
}