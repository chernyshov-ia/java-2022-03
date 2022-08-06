package ru.otus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.Dto.ClientDto;
import ru.otus.model.Client;
import ru.otus.services.DbServiceClient;

import java.util.List;

@Controller
@RequestMapping(path = "/clients")
public class ClientController {
    private final DbServiceClient clientService;

    public ClientController(DbServiceClient clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    String getClients(Model model) {
        List<ClientDto> clients = clientService.findAll().stream().map(ClientDto::valueOf).toList();
        model.addAttribute("clients", clients);
        return "clients";
    }
}

