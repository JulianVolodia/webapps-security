package mkolodziejski.webapp.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectHandler implements Handler {

    protected final String path;

    public RedirectHandler(String path) {
        this.path = path;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(request.getContextPath() + path);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
