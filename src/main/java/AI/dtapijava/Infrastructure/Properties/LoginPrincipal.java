package AI.dtapijava.Infrastructure.Properties;

import AI.dtapijava.Entities.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

@Getter
public class LoginPrincipal implements UserDetails {

    private User user;

    private Collection<? extends GrantedAuthority> authorities;

    private LoginPrincipal(User user) {
        this.user = user;
    }

    public static LoginPrincipal create(User user) {
        return new LoginPrincipal(user);
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginPrincipal that = (LoginPrincipal) o;
        return Objects.equals(user.getId(), that.user.getId());
    }

    @Override
    public String toString() {
        return "LoginPrincipal{" +
                "user=" + user.getName() +
                '}';
    }

}
