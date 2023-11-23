package nl.bsoft.monitoring.insuranceservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "policy")
public class Policy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "policytext")
    private String policyText;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Policy policy = (Policy) o;
        return id.equals(policy.id) && Objects.equals(policyText, policy.policyText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, policyText);
    }
}
