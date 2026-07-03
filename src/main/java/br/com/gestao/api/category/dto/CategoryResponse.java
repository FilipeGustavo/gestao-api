package br.com.gestao.api.category.dto;

import java.util.UUID;

public record CategoryResponse(
       UUID id,
       String name,
       String description
) {
}
