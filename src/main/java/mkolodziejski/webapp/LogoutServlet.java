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
public class LogoutServlet extends AbstractConnectionServlet {

    private static final Logger log = LoggerFactory.getLogger(LogoutServlet.class);

    private static final String TEMPLATE_NAME = "logout";

    @Inject
    private AuthenticationService authenticationService;

    @Override
    protected Handler processRequest(HttpServletRequest request, HttpServletResponse response) {
        if(!isUserLogged()) {
            return new RedirectHandler("/login");
        }

        doLogout();

        Map<String, Object> data = Maps.newHashMap();
        return new TemplateHandler(TEMPLATE_NAME, data);
    }


    private void doLogout() {
        authenticationService.logout();
    }
}
