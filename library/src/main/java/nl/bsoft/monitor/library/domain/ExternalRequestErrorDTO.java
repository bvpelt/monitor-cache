package nl.bsoft.monitor.library.domain;

public record ExternalRequestErrorDTO(String type, String title, Integer status, String detail, String instance) {
}
