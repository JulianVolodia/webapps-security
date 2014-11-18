package mkolodziejski.webapp;

import com.google.inject.Inject;
import com.google.inject.Injector;
import mkolodziejski.webapp.auth.AuthenticationInfo;
import mkolodziejski.webapp.handlers.Handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractConnectionServlet extends HttpServlet {

    @Inject
    protected Injector injector;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }


    protected final void process(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        doWithConnection(new Runnable() {
            @Override
            public void run() {
                Handler handler = processRequest(request, response);
                handler.handle(request, response);
            }
        });
    }


    protected final void doWithConnection(Runnable runnable) {
        Connection connection = injector.getInstance(Connection.class);

        runnable.run();

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    protected boolean isUserLogged() {
        AuthenticationInfo authenticationInfo = injector.getInstance(AuthenticationInfo.class);
        return authenticationInfo.getUserLogin() != null;
    }

    protected abstract Handler processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
