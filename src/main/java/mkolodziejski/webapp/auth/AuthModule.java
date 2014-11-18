package mkolodziejski.webapp.auth;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.servlet.SessionScoped;

public class AuthModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AuthenticationInfo.class).in(SessionScoped.class);
        bind(AuthenticationService.class).to(AuthenticationServiceImpl.class).in(Singleton.class);
    }
}
