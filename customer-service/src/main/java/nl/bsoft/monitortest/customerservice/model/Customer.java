package nl.bsoft.monitortest.customerservice.model;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Customer {
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
                ", policy='" + policy + '\'' +
                ", claim='" + claim + '\'' +
                '}';
    }

}