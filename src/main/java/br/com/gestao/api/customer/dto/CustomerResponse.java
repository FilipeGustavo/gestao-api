package br.com.gestao.api.customer.dto;

import java.time.Instant;
import java.util.UUID;

public record CustomerResponse(
        UUID id,
        String name,
        String email,
        String document,
        String phone,
        boolean active,
        Instant createdAt,
        Instant updatedAt
) {
}
