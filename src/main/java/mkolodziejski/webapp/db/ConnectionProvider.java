package mkolodziejski.webapp.db;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProvider implements Provider<Connection> {

    private static final Logger log = LoggerFactory.getLogger(ConnectionProvider.class);

    @Inject
    protected DataSource dataSource;

    @Override
    public Connection get() {

        log.info("Getting JDBC connection");

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
