package com.vladkrava.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Vlad Krava
 */

@Entity
@Table(name = "client")
public class Client implements Serializable {
    private int id;
    private String  name;

    public Client() {}

    public Client(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "client_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (id != client.id) return false;
        return !(name != null ? !name.equals(client.name) : client.name != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
