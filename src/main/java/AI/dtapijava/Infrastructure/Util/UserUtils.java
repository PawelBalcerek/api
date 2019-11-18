package AI.dtapijava.Infrastructure.Util;


import AI.dtapijava.Infrastructure.Properties.LoginPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public final class UserUtils {

    private UserUtils() {

    }

    public static Optional<String> getCurrentUserName() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(auth -> {
                    if (auth.getPrincipal() instanceof LoginPrincipal) {
                        LoginPrincipal springUser = (LoginPrincipal) auth.getPrincipal();
                        return springUser.getUser().getName();
                    } else if (auth.getPrincipal() instanceof String) {
                        return (String) auth.getPrincipal();
                    }
                    return null;
                });
    }

    public static Optional<LoginPrincipal> getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(auth -> {
                    if (auth.getPrincipal() instanceof LoginPrincipal) {
                        return (LoginPrincipal) auth.getPrincipal();
                    }
                    return null;
                });
    }

    public static Integer getCurrentUserId() {
        return getCurrentUser().get().getUser().getId();
    }


}
