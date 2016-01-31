package com.vladkrava.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Vlad Krava
 */

@Entity
@Table(name = "client_account")
public class ClientAccount implements Serializable {
    private Client client;
    private float bill;

    public ClientAccount() {}

    public ClientAccount(Client client, float bill) {
        this.client = client;
        this.bill = bill;
    }

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Column(name = "bill")
    public float getBill() {
        return bill;
    }

    public void setBill(float bill) {
        this.bill = bill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientAccount)) return false;

        ClientAccount that = (ClientAccount) o;

        if (Float.compare(that.bill, bill) != 0) return false;
        return !(client != null ? !client.equals(that.client) : that.client != null);

    }

    @Override
    public int hashCode() {
        int result = client != null ? client.hashCode() : 0;
        result = 31 * result + (bill != +0.0f ? Float.floatToIntBits(bill) : 0);
        return result;
    }
}
