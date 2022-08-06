package ru.otus.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;
import javax.annotation.Nonnull;

@Table("address")
public class Address {
    @Id
    private final Long id;

    private final Long clientId;

    @Nonnull
    private final String address;



    public Address(String address) {
        this(null, null, address);
    }

    @PersistenceCreator
    public Address(Long id, Long clientId, String address) {
        this.id = id;
        this.address = address;
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}
