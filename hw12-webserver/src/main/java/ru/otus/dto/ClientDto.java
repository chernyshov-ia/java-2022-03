package ru.otus.dto;

import ru.otus.model.Address;
import ru.otus.model.Client;
import ru.otus.model.Phone;

import java.util.List;

public class ClientDto {
    public Long id;
    public String name;
    public String address;
    public String phone;

    public ClientDto(Long id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public static ClientDto valueOf(Client client) {
        var address = client.getAddress().getAddress();
        var phone = client.getPhones().stream().map(Phone::getPhone).findFirst().orElse("");
        return new ClientDto(client.getId(), client.getName(), address, phone);
    }

    public Client toNewClient() {
        return new Client(null, name, new Address(null, address), List.of(new Phone(null, phone)) );
    }
}
