package ru.otus.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.eclipse.jetty.server.session.Session;
import ru.otus.dao.UserDao;
import ru.otus.dto.ClientDto;
import ru.otus.services.DbServiceClient;
import ru.otus.services.TemplateProcessor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ClientsServlet extends HttpServlet {

    private static final String USERS_PAGE_TEMPLATE = "clients.html";
    private static final String SESSION_ATTRIBUTE_NAME = "user_login";
    private static final String TEMPLATE_ATTR_USERNAME = "username";
    private static final String TEMPLATE_ATTR_CLIENTS = "clients";

    private final UserDao userDao;
    private final TemplateProcessor templateProcessor;
    private final DbServiceClient dbServiceClient;

    public ClientsServlet(TemplateProcessor templateProcessor, UserDao userDao, DbServiceClient dbServiceClient) {
        this.templateProcessor = templateProcessor;
        this.userDao = userDao;
        this.dbServiceClient = dbServiceClient;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, Object> paramsMap = new HashMap<>();

        HttpSession session = req.getSession(false);

        var username = session.getAttribute(SESSION_ATTRIBUTE_NAME);
        paramsMap.put(TEMPLATE_ATTR_USERNAME, username);

        var clients = dbServiceClient.findAll().stream()
                .map(ClientDto::valueOf)
                .toList();

        paramsMap.put(TEMPLATE_ATTR_CLIENTS, clients);

        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(USERS_PAGE_TEMPLATE, paramsMap));
    }

}
