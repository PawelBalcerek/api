package AI.dtapijava.Infrastructure.Properties;

import AI.dtapijava.Entities.User;
import AI.dtapijava.Exceptions.EmailNotExistException;
import AI.dtapijava.Exceptions.UserIDNotExistException;
import AI.dtapijava.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@Transactional
public class LoginDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletRequest request;


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotExistException("User not found with login or email : " + email));

        return LoginPrincipal.create(user);
    }

    public UserDetails loadUserById(Integer id) throws UsernameNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserIDNotExistException("User not found with id : " + id));
        LoginPrincipal loginPrincipal = LoginPrincipal.create(user);

        return loginPrincipal;
    }
}
