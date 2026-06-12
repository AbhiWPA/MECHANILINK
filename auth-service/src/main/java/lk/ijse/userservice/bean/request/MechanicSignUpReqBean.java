package lk.ijse.userservice.bean.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Title: mechani-link
 * Description: MechanicSignUpReqBean Class
 * Created by Abhishek Ashinsa on 11/25/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MechanicSignUpReqBean {


    private String specialization;
    private Integer yearsOfExperience;
    private Double hourlyRate;
    private Boolean isCertified;
    private String availableTimeSlots;
    private Double serviceRadiusKm;
    private String toolsAvailable;
    private String vehicleTypesSpecialized;
    private List<String> skills;
    private List<String> certifications;

//    // Workshop Information
//    private String workshopName;
//    private String workshopAddress;
//    private String workshopPhone;
//    private String workshopEmail;
//    private String workshopDescription;
//    private Integer establishedYear;
//    private Integer numberOfEmployees;

//    // Bank Details
//    private String bankName;
//    private String accountNumber;
//    private String accountHolderName;
//    private String bankBranch;

    // Skills
//    private List<String> skills;
//    private List<String> certifications;
}
