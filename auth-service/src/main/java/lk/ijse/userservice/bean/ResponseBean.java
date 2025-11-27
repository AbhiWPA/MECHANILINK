package lk.ijse.userservice.bean;

import java.io.Serializable;

import static lk.ijse.userservice.constant.AppConstant.*;


/**
 * Title: mechani-link
 * Description: ResponseBean Class
 * Created by Abhishek Ashinsa on 11/22/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */
    
public record ResponseBean (String status, String message, Object content) implements Serializable {

    public static ResponseBean createWithOutContent(String status, String message) {
        return new ResponseBean(status, message, null);
    }

    public static ResponseBean success(Object content) {
        return new ResponseBean(SUCCESS, "Success", content);
    }

    public static ResponseBean notfound(String message) {
        return new ResponseBean(NOT_FOUND, message, null);
    }

    public static ResponseBean unauthorized(String message) {
        return new ResponseBean(UNAUTHORIZED, message, null);
    }

    public static ResponseBean exists(Object content) {return new ResponseBean(USER_ALREADY_EXISTS, "User Already Exists!", content);
    }
}
