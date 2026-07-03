package br.com.gestao.api.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest (
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 120, message = "Nome pode ter no máximo 120 caracteres")
        String name,

        @NotBlank(message = "Descrição é obrigatório")
        @Size(max = 200, message = "Descrição pode ter no máximo 200 caracteres")
        String description
) {
}
