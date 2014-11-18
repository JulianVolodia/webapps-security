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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Singleton
public class MessagesServlet extends AbstractConnectionServlet {

    private static final Logger log = LoggerFactory.getLogger(MessagesServlet.class);

    private static final String TEMPLATE_NAME = "messages";

    private static final String MESSASGE_SUBJECT_FIELD = "message_subject";
    private static final String MESSASGE_CONTENT_FIELD = "message_content";
    private static final String MESSASGE_RECIPIENT_ID_FIELD = "recipient_id";

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

        // sending message
        if(eventuallySendMessage(request, userAccount)) {
            data.put("message_sent", true);
        }

        // messages
        List<PrivateMessageView> receivedMessages = privateMessagesService.getAllReceivedMessages(userAccount.getId());
        data.put("received_messages", receivedMessages);

        List<PrivateMessageView> sentMessages = privateMessagesService.getAllSentMessages(userAccount.getId());
        data.put("sent_messages", sentMessages);

        return new TemplateHandler(TEMPLATE_NAME, data);
    }


    private boolean eventuallySendMessage(HttpServletRequest request, UserAccount userAccount) {
        String messageContent = request.getParameter(MESSASGE_CONTENT_FIELD);
        String messageSubject = request.getParameter(MESSASGE_SUBJECT_FIELD);
        String recipientIdString = request.getParameter(MESSASGE_RECIPIENT_ID_FIELD);
        if(Strings.isNullOrEmpty(messageContent)
                || Strings.isNullOrEmpty(messageSubject)
                || Strings.isNullOrEmpty(recipientIdString)) {
            return false;
        }

        int recipientId = Integer.parseInt(recipientIdString);

        log.debug("Message content provided. Recipient: {}, subject: '{}', content: '{}'", recipientId, messageSubject, messageContent);

        privateMessagesService.insertMessage(userAccount.getId(), recipientId, messageSubject, messageContent);
        return true;
    }

}
