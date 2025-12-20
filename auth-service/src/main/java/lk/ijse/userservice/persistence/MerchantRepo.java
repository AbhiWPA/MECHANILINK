package lk.ijse.userservice.persistence;

import lk.ijse.userservice.persistence.entity.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Title: mechani-link
 * Description: MerchantRepo Class
 * Created by Abhishek Ashinsa on 11/28/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */
@Repository
public interface MerchantRepo extends JpaRepository<MerchantEntity, String> {
}
