package mkolodziejski.webapp;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import mkolodziejski.webapp.auth.AuthenticationInfo;
import mkolodziejski.webapp.handlers.Handler;
import mkolodziejski.webapp.handlers.RedirectHandler;
import mkolodziejski.webapp.handlers.TemplateHandler;
import mkolodziejski.webapp.messages.PrivateMessageView;
import mkolodziejski.webapp.messages.PrivateMessagesService;
import mkolodziejski.webapp.users.UserAccount;
import mkolodziejski.webapp.users.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Singleton
public class MessageDetailsServlet extends AbstractConnectionServlet {

    private static final String TEMPLATE_NAME = "message_details";

    private static final String MESSASGE_ID_PARAM = "message_id";


    @Inject
    private UsersService usersService;
    @Inject
    private PrivateMessagesService privateMessagesService;
    @Inject
    private Provider<AuthenticationInfo> authenticationInfoProvider;

    @Override
    protected Handler processRequest(HttpServletRequest request, HttpServletResponse response) {
        if(!isUserLogged()) {
            return new RedirectHandler("/login");
        }

        AuthenticationInfo authenticationInfo = authenticationInfoProvider.get();
        UserAccount userAccount = usersService.getUserAccountByLogin(authenticationInfo.getUserLogin());

        // logged user info
        Map<String, Object> data = Maps.newHashMap();
        data.put("user", userAccount);

        // messages
        String messageId = request.getParameter(MESSASGE_ID_PARAM);
        if(Strings.isNullOrEmpty(messageId)) {
            throw new IllegalStateException("Lack of required '" + MESSASGE_ID_PARAM + "' parameter!");
        }
        PrivateMessageView message = privateMessagesService.getMessage(messageId);
        data.put("message", message);

        return new TemplateHandler(TEMPLATE_NAME, data);
    }
}
