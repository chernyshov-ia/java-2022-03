package ru.otus.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.Nonnull;

@Table("client_phone")
public class Phone {
    @Id
    private final Long id;

    @Nonnull
    private final Long clientId;

    @Nonnull
    private final String phone;

    public Phone(String phone) {
        this(null, null, phone);
    }

    @PersistenceCreator
    public Phone(Long id, Long clientId, String phone) {
        this.id = id;
        this.clientId = clientId;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public Long getClientId() {
        return clientId;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                '}';
    }
}
