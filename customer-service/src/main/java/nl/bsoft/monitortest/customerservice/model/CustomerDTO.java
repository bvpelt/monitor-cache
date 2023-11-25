package nl.bsoft.monitortest.customerservice.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "customer")
public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer customerId;
    private Integer policyId;
    private Integer claimId;

    public CustomerDTO() {
    }

    public CustomerDTO(Integer customerId, Integer policyId, Integer claimId) {
        this.customerId = customerId;
        this.policyId = policyId;
        this.claimId = claimId;
    }

    @Override
    public String toString() {
        return "CustomerPojo{" +
                "customerId=" + customerId +
                ", policyId='" + policyId + '\'' +
                ", claimId='" + claimId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO customer = (CustomerDTO) o;
        return customerId.equals(customer.customerId) && Objects.equals(policyId, customer.policyId) && Objects.equals(claimId, customer.claimId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, policyId, claimId);
    }
}