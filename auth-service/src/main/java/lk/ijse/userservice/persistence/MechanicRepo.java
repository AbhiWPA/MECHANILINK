package lk.ijse.userservice.persistence;

import lk.ijse.userservice.persistence.entity.MechanicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Title: mechani-link
 * Description: MechanicRepo Class
 * Created by Abhishek Ashinsa on 11/28/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */
@Repository
public interface MechanicRepo extends JpaRepository<MechanicEntity, String> {
}
