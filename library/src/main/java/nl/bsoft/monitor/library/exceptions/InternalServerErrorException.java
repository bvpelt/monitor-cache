package nl.bsoft.monitor.library.exceptions;

import jakarta.persistence.criteria.CriteriaBuilder;

public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(Exception e) {
        super(e);
    }
}
