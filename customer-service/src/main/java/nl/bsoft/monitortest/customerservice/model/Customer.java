package nl.bsoft.monitortest.customerservice.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name="customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String policy;
    private String claim;

    public Customer() {
    }

    public Customer(Integer customerId, String policy, String claim) {
        this.customerId = customerId;
        this.policy = policy;
        this.claim = claim;
    }

    @Override
    public String toString() {
        return "CustomerPojo{" +
                "customerId=" + customerId +
                ", policyId='" + policy + '\'' +
                ", claimId='" + claim + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer that = (Customer) o;
        return customerId.equals(that.customerId) && policy.equals(that.policy) && claim.equals(that.claim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, policy, claim);
    }
}