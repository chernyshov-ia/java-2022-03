package ru.otus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.Dto.ClientDto;
import ru.otus.services.DbServiceClient;
import ru.otus.services.DbServiceClientImpl;

@RestController
@RequestMapping(path = "/api/client")
public class ClientRestController {
    private static final Logger log = LoggerFactory.getLogger(DbServiceClientImpl.class);

    private final DbServiceClient dbServiceClient;

    public ClientRestController(DbServiceClient dbServiceClient) {
        this.dbServiceClient = dbServiceClient;
    }

    @PostMapping
    ClientDto postClient(@RequestBody ClientDto clientDto) {
        log.info(clientDto.toString());
        var client = clientDto.toNewClient();
        client = dbServiceClient.saveClient(client);
        return ClientDto.valueOf(client);
    }
}


