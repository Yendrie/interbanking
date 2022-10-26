package org.interbanking.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String address;
    private String rut;
    private String enterpriseId;

    public Client(String name, String lastname, String email, String phone, String address, String rut, String enterpriseId) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.rut = rut;
        this.enterpriseId = enterpriseId;
    }

    public Client() {

    }
}
