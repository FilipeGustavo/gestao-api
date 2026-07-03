package br.com.gestao.api.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Nome e obrigatorio")
        @Size(max = 120, message = "Nome deve ter no maximo 120 caracteres")
        String name,

        @Size(max = 500, message = "Descricao deve ter no maximo 500 caracteres")
        String description,

        @NotBlank(message = "SKU e obrigatorio")
        @Size(max = 60, message = "SKU deve ter no maximo 60 caracteres")
        String sku,

        @NotNull(message = "Preco e obrigatorio")
        @DecimalMin(value = "0.01", message = "Preco deve ser maior que zero")
        BigDecimal price,

        @NotNull(message = "Quantidade em estoque e obrigatoria")
        @Min(value = 0, message = "Quantidade em estoque nao pode ser negativa")
        Integer stockQuantity,

        Boolean active
) {
}
