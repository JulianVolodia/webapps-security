package mkolodziejski.webapp;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import mkolodziejski.webapp.auth.AuthModule;
import mkolodziejski.webapp.db.DataSourceModule;
import mkolodziejski.webapp.messages.PrivateMessagesModule;
import mkolodziejski.webapp.posts.PostsModule;
import mkolodziejski.webapp.users.UsersModule;

public class GuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new GuiceServletModule(),
                new DataSourceModule(),
                new AuthModule(),
                new UsersModule(),
                new PostsModule(),
                new PrivateMessagesModule());
    }
}
