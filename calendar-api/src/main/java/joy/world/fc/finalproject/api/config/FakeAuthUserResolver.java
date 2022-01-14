package joy.world.fc.finalproject.api.config;

import joy.world.fc.finalproject.api.dto.AuthUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static joy.world.fc.finalproject.api.service.LoginService.LOGIN_SESSION_KEY;

public class FakeAuthUserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return AuthUser.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        final String userIdStr = webRequest.getParameter("userId");
        if (userIdStr == null) {
            return new AuthUserResolver().resolveArgument(parameter, mavContainer, webRequest, binderFactory);
        }
        return AuthUser.of(Long.parseLong(userIdStr));
    }

}
