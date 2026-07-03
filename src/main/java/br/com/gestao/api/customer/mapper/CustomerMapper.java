package br.com.gestao.api.customer.mapper;

import br.com.gestao.api.customer.domain.Customer;
import br.com.gestao.api.customer.dto.CustomerRequest;
import br.com.gestao.api.customer.dto.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequest request) {
        return new Customer(
                request.name().trim(),
                request.email().trim().toLowerCase(),
                normalize(request.document()),
                normalizeNullable(request.phone())
        );
    }

    public CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getDocument(),
                customer.getPhone(),
                customer.isActive(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

    public void updateEntity(Customer customer, CustomerRequest request) {
        customer.update(
                request.name().trim(),
                request.email().trim().toLowerCase(),
                normalize(request.document()),
                normalizeNullable(request.phone()),
                request.active() == null || request.active()
        );
    }

    private String normalize(String value) {
        return value.replaceAll("\\D", "");
    }

    private String normalizeNullable(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.replaceAll("\\D", "");
    }
}
