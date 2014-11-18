package mkolodziejski.webapp;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import mkolodziejski.webapp.auth.AuthenticationInfo;
import mkolodziejski.webapp.auth.AuthenticationService;
import mkolodziejski.webapp.handlers.Handler;
import mkolodziejski.webapp.handlers.RedirectHandler;
import mkolodziejski.webapp.handlers.TemplateHandler;
import mkolodziejski.webapp.posts.PostMessagesService;
import mkolodziejski.webapp.posts.WallPost;
import mkolodziejski.webapp.users.UserAccount;
import mkolodziejski.webapp.users.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Singleton
public class HomeServlet extends AbstractConnectionServlet {

    private static final Logger log = LoggerFactory.getLogger(HomeServlet.class);

    private static final String TEMPLATE_NAME = "home";

    private static final String POST_CONTENT_FIELD = "post_content";

    @Inject
    private AuthenticationService authenticationService;
    @Inject
    private UsersService usersService;
    @Inject
    private PostMessagesService postMessagesService;
    @Inject
    private Provider<AuthenticationInfo> authenticationInfoProvider;

    @Override
    protected Handler processRequest(HttpServletRequest request, HttpServletResponse response) {
        if(!isUserLogged()) {
            return new RedirectHandler("/login");
        }

        AuthenticationInfo authenticationInfo = authenticationInfoProvider.get();
        UserAccount userAccount = usersService.getUserAccountByLogin(authenticationInfo.getUserLogin());


        // eventually post
        eventuallyPostMessage(request, userAccount);


        // logged user info
        Map<String, Object> data = Maps.newHashMap();
        data.put("user", userAccount);

        // posts
        List<WallPost> wallPosts = postMessagesService.getWallPosts(userAccount.getId());
        data.put("wall_posts", wallPosts);

        return new TemplateHandler(TEMPLATE_NAME, data);
    }


    private void eventuallyPostMessage(HttpServletRequest request, UserAccount userAccount) {
        String postContent = request.getParameter(POST_CONTENT_FIELD);
        if(Strings.isNullOrEmpty(postContent)) {
            return;
        }

        log.debug("Post content provided: {}", postContent);

        postMessagesService.insertPostMessage(userAccount.getId(), postContent);
    }
}
