package nl.bsoft.monitoring.insuranceservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name="claim")
public class Claim implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String claimText;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Claim claim = (Claim) o;
        return id.equals(claim.id) && Objects.equals(claimText, claim.claimText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, claimText);
    }
}
