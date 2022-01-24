package com.bftcom.LearnTask.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class authors {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login, password;

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
