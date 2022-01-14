package joy.world.fc.finalproject.api.service;

import joy.world.fc.finalproject.api.dto.LoginRequest;
import joy.world.fc.finalproject.api.dto.SignUpRequest;
import joy.world.fc.finalproject.core.dto.UserCreateRequest;
import joy.world.fc.finalproject.core.entity.User;
import joy.world.fc.finalproject.core.exception.CalendarException;
import joy.world.fc.finalproject.core.exception.ErrorCode;
import joy.world.fc.finalproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserService userService;
    public final static String LOGIN_SESSION_KEY = "USER_ID";

    @Transactional
    //UserService Create 를 담당한다 .
    public void signUp(SignUpRequest request, HttpSession session) {
        final User user = userService.create(new UserCreateRequest(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getBirthday()));
        session.setAttribute(LOGIN_SESSION_KEY, user.getId());
    }
    @Transactional
    public void login(LoginRequest request, HttpSession session) {
        final Long userId = (Long) session.getAttribute(LOGIN_SESSION_KEY);
        if (userId != null) {
            return;
        }
        final Optional<User> user = userService.findPwMatchUser(request.getEmail(), request.getPassword());
        if (user.isPresent()) {
            session.setAttribute(LOGIN_SESSION_KEY, user.get().getId());
        } else {
            throw new CalendarException(ErrorCode.PASSWORD_NOT_MATCH);
        }
    }

    public void logout(HttpSession session) {
        session.removeAttribute(LOGIN_SESSION_KEY);
    }

}
