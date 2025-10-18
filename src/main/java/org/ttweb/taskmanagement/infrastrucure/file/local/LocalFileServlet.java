package org.ttweb.taskmanagement.infrastrucure.file.local;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/local-file/*")
public class LocalFileServlet extends HttpServlet {
    private static final long serialVersionUID = -3252048715932766336L;

    private static final Logger log = LoggerFactory.getLogger(LocalFileServlet.class);

    private String localRootPath;
    private Environment environment;

    public LocalFileServlet(@Value("${app.file-storage.local-root-folder}") String localRootPath,
                            Environment environment) {
        this.localRootPath = localRootPath;
        this.environment = environment;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (environment.acceptsProfiles("production", "staging")) {
            String activeProfiles = String.join(", ", environment.getActiveProfiles());
            log.warn("Access `{}` in environment `{}`. IP address: `{}` ", request.getPathInfo(), activeProfiles, request.getRemoteAddr());
        }

        String pathInfo = request.getPathInfo();
        if ("/".equals(pathInfo)) {
            response.getWriter().write("/");
            return;
        }

        String filePath = localRootPath + request.getPathInfo();
        File file = new File(filePath);
        if (!file.exists() || file.isDirectory()) {
            response.sendError(404);
            return;
        }

        response.setContentType(request.getServletContext().getMimeType(pathInfo));
        response.setHeader("Cache-Control", "public, max-age=31536000");
        Files.copy(Paths.get(localRootPath, pathInfo), response.getOutputStream());
    }
}
