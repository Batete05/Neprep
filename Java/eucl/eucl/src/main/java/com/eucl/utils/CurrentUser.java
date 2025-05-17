package com.eucl.utils;

import com.eucl.exceptions.CustomRequestException;
import com.eucl.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class CurrentUser {

    public static User getCurrentUser() throws CustomRequestException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails userDetails) {

                return (User) userDetails;
            } else {
                throw new CustomRequestException(HttpStatus.INTERNAL_SERVER_ERROR, "something went wrong");
            }
        } else {
            throw new CustomRequestException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
    }
}
