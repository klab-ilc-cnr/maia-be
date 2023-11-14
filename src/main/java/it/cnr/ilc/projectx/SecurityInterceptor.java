package it.cnr.ilc.projectx;

import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.service.AuthenticationService;
import it.cnr.ilc.projectx.service.AuthenticationService.AuthenticationException;
import it.cnr.ilc.projectx.utils.LoggedUserHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author oakgen
 */
@RequiredArgsConstructor
public class SecurityInterceptor implements HandlerInterceptor {

    @NonNull
    private final AuthenticationService authenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AuthenticationException {
        if (!request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            String jwtToken = request.getHeader("authorization");
            if (jwtToken == null) {
                throw new AuthenticationException("jwt token missing");
            }
            if (!jwtToken.toLowerCase().startsWith("bearer ")) {
                throw new AuthenticationException("invalid token type");
            }
            jwtToken = jwtToken.substring(7);
            UserDto userDto = authenticationService.validate(jwtToken);
            LoggedUserHolder.setUser(userDto);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        LoggedUserHolder.remove();
    }
}
