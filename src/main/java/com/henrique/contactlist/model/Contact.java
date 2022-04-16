package com.henrique.contactlist.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "contato")
@Getter
@Setter
public class Contact {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="nome")
    private String name;

    @Column(name="telefone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="uuid")
    private String uuid;
}
