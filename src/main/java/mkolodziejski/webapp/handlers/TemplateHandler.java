package mkolodziejski.webapp.handlers;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class TemplateHandler implements Handler {

    protected final String templateName;
    protected final Map<String, Object> data;

    public TemplateHandler(String templateName, Map<String, Object> data) {
        this.templateName = templateName;
        this.data = data;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        Configuration cfg = new Configuration();
        cfg.setDefaultEncoding("UTF-8");
        cfg.setOutputEncoding("UTF-8");
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setServletContextForTemplateLoading(request.getServletContext(), "/WEB-INF/templates");

        renderTemplate(response, cfg);
    }


    protected void renderTemplate(HttpServletResponse response, Configuration cfg) {
        try {
            Template template = cfg.getTemplate(templateName + ".ftl");
            template.process(data, response.getWriter());

        } catch (TemplateException e) {
            throw new RuntimeException("Template rendering failed", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
