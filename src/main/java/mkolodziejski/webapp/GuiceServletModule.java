package mkolodziejski.webapp;

import com.google.inject.servlet.ServletModule;

public class GuiceServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        serve("/").with(HomeServlet.class);
        serve("/login").with(LoginServlet.class);
        serve("/logout").with(LogoutServlet.class);
        serve("/messages").with(MessagesServlet.class);
        serve("/message_details").with(MessageDetailsServlet.class);

        // static content
        serve("/css/*").with(StaticContentServlet.class);
    }
}
