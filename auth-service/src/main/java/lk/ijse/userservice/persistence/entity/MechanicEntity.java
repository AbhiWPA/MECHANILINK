package lk.ijse.userservice.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lk.ijse.userservice.util.StatusConstant;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: mechani-link
 * Description: MechanicEntity Class
 * Created by Abhishek Ashinsa on 11/25/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */
@Entity
@Table(name = "mechanic")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "user")
public class MechanicEntity {

    @Id
    @Column(name = "id", length = 50)
    private String id;

    @OneToOne
    @JoinColumn(name = "id")
    @JsonIgnoreProperties({"mechanic", "merchant"})
    private UserEntity user;

    // Professional Information
    private String specialization;
    private Integer yearsOfExperience;
    private Double hourlyRate;

    @Column(name = "is_certified")
    private Boolean isCertified;

    private String availableTimeSlots;
    private Double serviceRadiusKm;
    private String toolsAvailable;
    private String vehicleTypesSpecialized;

    // Skills & Certifications
    @ElementCollection
    @CollectionTable(name = "mechanic_skills", joinColumns = @JoinColumn(name = "mechanic_id"))
    @Column(name = "skill")
    private List<String> skills = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "mechanic_certifications", joinColumns = @JoinColumn(name = "mechanic_id"))
    @Column(name = "certification")
    private List<String> certifications = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "mechanic_status")
    private StatusConstant status;
}