package mkolodziejski.webapp;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import mkolodziejski.webapp.auth.AuthenticationService;
import mkolodziejski.webapp.auth.AuthenticationStatus;
import mkolodziejski.webapp.handlers.Handler;
import mkolodziejski.webapp.handlers.RedirectHandler;
import mkolodziejski.webapp.handlers.TemplateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Singleton
public class LoginServlet extends AbstractConnectionServlet {

    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    private static final String TEMPLATE_NAME = "login";

    private static final String LOGIN_FIELD_NAME = "login";
    private static final String PASSWORD_FIELD_NAME = "passwd";

    @Inject
    private AuthenticationService authenticationService;

    @Override
    protected Handler processRequest(HttpServletRequest request, HttpServletResponse response) {
        if(isUserLogged()) {
            return new RedirectHandler("/");
        }

        Map<String, Object> data = Maps.newHashMap();

        String login = request.getParameter(LOGIN_FIELD_NAME);
        String password = request.getParameter(PASSWORD_FIELD_NAME);
        if(!Strings.isNullOrEmpty(login) && !Strings.isNullOrEmpty(password)) {
            log.debug("Login and password provided - authenticating");

            AuthenticationStatus authenticationStatus = doLogin(login, password);

            if(AuthenticationStatus.OK.equals(authenticationStatus)) {
                // logged in - redirecting
                log.info("User '{}' authenticated successfully", login);
                return new RedirectHandler("/");

            } else {
                log.info("User '{}' authentication failed", login);
                data.put("authFailed", true);
            }
        }

        return new TemplateHandler(TEMPLATE_NAME, data);
    }


    private AuthenticationStatus doLogin(String login, String password) {
        return authenticationService.authenticate(login, password);
    }
}
