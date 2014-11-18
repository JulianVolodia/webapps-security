package mkolodziejski.webapp.db;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.servlet.RequestScoped;

import javax.sql.DataSource;
import java.sql.Connection;

public class DataSourceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DataSource.class).toProvider(DataSourceProvider.class).in(Singleton.class);
        bind(Connection.class).toProvider(ConnectionProvider.class).in(RequestScoped.class);
    }
}
