package br.com.gestao.api.product.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String description,
        String sku,
        BigDecimal price,
        Integer stockQuantity,
        boolean active,
        Instant createdAt,
        Instant updatedAt
) {
}
