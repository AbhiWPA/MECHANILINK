package lk.ijse.userservice.service;

import io.jsonwebtoken.Claims;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lk.ijse.userservice.persistence.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

/**
 * Title: mechani-link
 * Description: JwtService Class
 * Created by Abhishek Ashinsa on 11/23/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */

public interface JwtService {

    String extractUserEmail(String authToken);

    Claims extractAllClaims(String authToken);

    String generateToken(Map<String, Object> extractClaims, UserDetails userDetails);
    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String authToken, UserDetails userDetails);
}
