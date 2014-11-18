package mkolodziejski.webapp.posts;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class PostsModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PostMessagesService.class).to(PostMessageServiceImpl.class).in(Singleton.class);
    }
}
