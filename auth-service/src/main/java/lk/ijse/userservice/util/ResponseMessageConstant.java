package lk.ijse.userservice.util;

/**
 * Title: mechani-link
 * Description: ResponseMessageConstant Class
 * Created by Abhishek Ashinsa on 11/27/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */

public class ResponseMessageConstant {

    public static final String USER_NOT_FOUND = "User not found";
    public static final String INVALID_USER_ID = "Invalid User ID";
    public static final String TOKEN_FETCH_FAILED = "Token fetch failed";
    public static final String TOKEN_NOT_FOUND = "Token not found";
    public static final String INVALID_TOKEN = "Invalid token";
    public static final String INCORRECT_OLD_PASSWORD = "Incorrect old password";
    private ResponseMessageConstant() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
