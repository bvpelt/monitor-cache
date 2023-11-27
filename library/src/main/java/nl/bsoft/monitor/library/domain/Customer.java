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

    private Long customerId;
    private Long policyId;
    private Long claimId;

    public Customer() {
    }

    public Customer(Long customerId, Long policyId, Long claimId) {
        this.customerId = customerId;
        this.policyId = policyId;
        this.claimId = claimId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", policyId='" + policyId + '\'' +
                ", claimId='" + claimId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) && Objects.equals(customerId, customer.customerId) && Objects.equals(policyId, customer.policyId) && Objects.equals(claimId, customer.claimId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, policyId, claimId);
    }
}