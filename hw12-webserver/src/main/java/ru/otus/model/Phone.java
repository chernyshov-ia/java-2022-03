package ru.otus.model;

import javax.persistence.*;

@Entity
@Table(name = "client_phone")
public class Phone implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "phone")
    private String phone;

    public Phone() {
    }

    public Phone(Long id, String phone) {
        this.id = id;
        this.phone = phone;
    }

    public Phone(String phone) {
        this.phone = phone;
    }

    @Override
    protected Phone clone() {
        return new Phone(this.id, this.phone);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                '}';
    }
}
