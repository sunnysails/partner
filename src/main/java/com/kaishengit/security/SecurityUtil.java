package com.kaishengit.security;

import com.kaishengit.dto.MyUserDetails;
import com.kaishengit.pojo.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by sunny on 2017/3/24.
 */
public class SecurityUtil {

    public static MyUserDetails getCurrentDetailsUse() {
        return (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
    public static User getCurrentUser() {
        User user = new User();
        user.setId(getCurrentUserId());
        user.setUserName(getCurrentUserName());
        return user;
    }

    public static String getCurrentUserName() {
        return getCurrentDetailsUse().getUsername();
    }

    public static Integer getCurrentUserId() {
        return getCurrentDetailsUse().getId();
    }
}
