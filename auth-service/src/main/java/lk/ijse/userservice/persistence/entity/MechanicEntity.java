package lk.ijse.userservice.persistence.entity;

import jakarta.persistence.*;
import lk.ijse.userservice.util.StatusConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class MechanicEntity {
    @Id
    @Column(name = "id")
    private String id;


    @OneToOne
    @JoinColumn(name = "id")
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
