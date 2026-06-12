package lk.ijse.userservice.service;

import io.jsonwebtoken.Claims;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lk.ijse.userservice.persistence.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * Title: mechani-link
 * Description: JwtService Class
 * Created by Abhishek Ashinsa on 11/23/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */

public interface JwtService {

    /**
     * Extract username from JWT token
     * @param token JWT token
     * @return username from token
     */
    String extractUsername(String token);

    /**
     * Extract user ID from JWT token
     * @param token JWT token
     * @return user ID from token
     */
    String extractUserId(String token);

    /**
     * Extract role from JWT token
     * @param token JWT token
     * @return role from token
     */
    String extractRole(String token);

    /**
     * Extract specific claim from JWT token
     * @param token JWT token
     * @param claimsResolver function to resolve claims
     * @return extracted claim
     */
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    /**
     * Generate JWT token for user
     * @param user UserEntity object
     * @return generated JWT token
     */
    String generateToken(UserEntity user);

    /**
     * Generate JWT token with custom claims
     * @param extraClaims additional claims to include
     * @param subject subject (username/email)
     * @return generated JWT token
     */
    String generateToken(Map<String, Object> extraClaims, String subject);

    /**
     * Validate JWT token
     * @param token JWT token
     * @param userDetails UserDetails object
     * @return true if token is valid
     */
    boolean isTokenValid(String token, UserDetails userDetails);

    /**
     * Check if token is expired
     * @param token JWT token
     * @return true if token is expired
     */
    boolean isTokenExpired(String token);

    /**
     * Extract expiration date from token
     * @param token JWT token
     * @return expiration date
     */
    Date extractExpiration(String token);

    /**
     * Extract all claims from token
     * @param token JWT token
     * @return all claims
     */
    Claims extractAllClaims(String token);

    /**
     * Get signing key for JWT
     * @return Key object for signing
     */
    Key getSignInKey();
}
