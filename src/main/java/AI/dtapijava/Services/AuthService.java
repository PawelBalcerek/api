package AI.dtapijava.Services;

import AI.dtapijava.Components.ExecDetailsHelper;
import AI.dtapijava.DTOs.Request.AuthReqDTO;
import AI.dtapijava.DTOs.Response.AuthResDTO;
import AI.dtapijava.DTOs.Response.JwtAuthResDTO;
import AI.dtapijava.Infrastructure.Properties.JwtTokenProvider;
import AI.dtapijava.Infrastructure.Properties.LoginPrincipal;
import AI.dtapijava.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class AuthService {

    private final Logger log = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;


    public AuthResDTO getSigninCredential(AuthReqDTO authReqDTO){
        ExecDetailsHelper execHelper = new ExecDetailsHelper();
        log.debug("Try login by user {}", authReqDTO.getEmail());

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authReqDTO.getEmail(),authReqDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        return new AuthResDTO(new JwtAuthResDTO(jwt),
                ((LoginPrincipal) authentication.getPrincipal()).getUser(),
                null,
                execHelper.getExecTimeWithEndExecTime(OffsetDateTime.now())
                );
    }

}
