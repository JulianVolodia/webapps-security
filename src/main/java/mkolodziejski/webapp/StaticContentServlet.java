package mkolodziejski.webapp;

import com.google.common.collect.ImmutableList;
import com.google.common.io.ByteStreams;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Singleton
public class StaticContentServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(StaticContentServlet.class);

    private static final ImmutableList<String> VALID_PATHS = ImmutableList.of("/css");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkPath(request);

        String internalPath = "/WEB-INF" + request.getServletPath() + request.getPathInfo();

        InputStream inputStream = getServletContext().getResourceAsStream(internalPath);
        ServletOutputStream outputStream = response.getOutputStream();
        ByteStreams.copy(inputStream, outputStream);
    }

    private void checkPath(HttpServletRequest request) {
        if(!VALID_PATHS.contains(request.getServletPath())) {
            throw new IllegalStateException("Invalid path: " + request.getServletPath());
        }
    }
}
