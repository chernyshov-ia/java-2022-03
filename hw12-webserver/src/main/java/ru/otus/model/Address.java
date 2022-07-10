package ru.otus.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "address")
    private String address;

    public Address() {
    }

    public Address(String address) {
        this.address = address;
    }

    public Address(Long id, String address) {
        this.id = id;
        this.address = address;
    }

    @Override
    protected Address clone() {
        return new Address(this.id, this.address);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String phone) {
        this.address = phone;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}
