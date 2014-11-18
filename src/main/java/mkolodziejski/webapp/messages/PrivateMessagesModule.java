package mkolodziejski.webapp.messages;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class PrivateMessagesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PrivateMessagesService.class).to(PrivateMessagesServiceImpl.class).in(Singleton.class);
    }
}
