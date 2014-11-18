package mkolodziejski.webapp.db;

import com.google.inject.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceProvider implements Provider<DataSource> {

    private static final Logger log = LoggerFactory.getLogger(DataSourceProvider.class);

    protected DataSource dataSource;

    @Override
    public DataSource get() {

        log.info("Getting JDBC DataSource");

        if(dataSource == null) {
            try {
                Context ctxt = new InitialContext();
                dataSource = (DataSource)(ctxt).lookup("webappDS");
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
        }

        return dataSource;
    }
}
