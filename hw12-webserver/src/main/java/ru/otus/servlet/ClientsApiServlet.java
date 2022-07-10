package ru.otus.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.otus.dto.ClientDto;
import ru.otus.model.Address;
import ru.otus.model.Client;
import ru.otus.model.Phone;
import ru.otus.services.DbServiceClient;


import java.io.IOException;
import java.util.List;

public class ClientsApiServlet extends HttpServlet {

    private final Gson gson;
    private final DbServiceClient dbServiceClient;

    public ClientsApiServlet(Gson gson, DbServiceClient dbServiceClient) {
        this.gson = gson;
        this.dbServiceClient = dbServiceClient;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var clientDto = gson.fromJson(request.getReader(), ClientDto.class);

        var client = clientDto.toNewClient();
        client = dbServiceClient.saveClient(client);
        clientDto = ClientDto.valueOf(client);

        response.setContentType("application/json;charset=UTF-8");

        var json = gson.toJson(clientDto);
        response.getOutputStream().print(json);
    }

}
