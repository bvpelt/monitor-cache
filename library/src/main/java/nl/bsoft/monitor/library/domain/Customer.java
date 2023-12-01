package nl.bsoft.monitor.library.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="customerid")
    private Long customerid;
    @Column(name="policyid")
    private Long policyid;
    @Column(name="claimid")
    private Long claimid;

    public Customer() {
    }

    public Customer(Long customerid, Long policyid, Long claimid) {
        this.customerid = customerid;
        this.policyid = policyid;
        this.claimid = claimid;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerid +
                ", policyId='" + policyid + '\'' +
                ", claimId='" + claimid + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) && Objects.equals(customerid, customer.customerid) && Objects.equals(policyid, customer.policyid) && Objects.equals(claimid, customer.claimid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerid, policyid, claimid);
    }
}