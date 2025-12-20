package lk.ijse.userservice.persistence;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lk.ijse.userservice.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Title: mechani-link
 * Description: UserRepo Class
 * Created by Abhishek Ashinsa on 11/23/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);

//    Optional<UserEntity> findByEmailOrId(@Email(message = "Valid email is required") String email, @NotNull String id);

    boolean existsByEmailOrId(@Email(message = "Valid email is required") String email, @NotNull String id);
}
