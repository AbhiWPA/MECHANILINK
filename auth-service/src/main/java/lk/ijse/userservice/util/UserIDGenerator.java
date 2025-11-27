package lk.ijse.userservice.util;

import java.util.UUID;

/**
 * Title: mechani-link
 * Description: UserIDGenerator Class
 * Created by Abhishek Ashinsa on 11/26/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */

public class UserIDGenerator {

    public static String generateUserId(Role role) {
        String rolePrefix = getRolePrefix(role);
        String uuid = generateShortUUID();

        return rolePrefix + "_" + uuid;
    }

    private static String getRolePrefix(Role role) {
        switch (role) {
            case MERCHANT: return "MERCH";
            case MECHANIC: return "MECH";
            case DRIVER: return "DRIV";
            default: return "USER";
        }
    }

    private static String generateShortUUID() {
        return UUID.randomUUID().toString()
                .substring(0, 8)
                .toUpperCase()
                .replace("-", "");
    }

}
