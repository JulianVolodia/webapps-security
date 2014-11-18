package mkolodziejski.webapp.users;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class UsersModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UsersService.class).to(UsersServiceImpl.class).in(Singleton.class);
    }
}
