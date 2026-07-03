package br.com.gestao.api.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerRequest(
        @NotBlank(message = "Nome e obrigatorio")
        @Size(max = 120, message = "Nome deve ter no maximo 120 caracteres")
        String name,

        @NotBlank(message = "E-mail e obrigatorio")
        @Email(message = "E-mail deve ser valido")
        @Size(max = 160, message = "E-mail deve ter no maximo 160 caracteres")
        String email,

        @NotBlank(message = "Documento e obrigatorio")
        @Pattern(regexp = "\\d{11}|\\d{14}", message = "Documento deve conter 11 ou 14 digitos")
        String document,

        @Size(max = 20, message = "Telefone deve ter no maximo 20 caracteres")
        String phone,

        Boolean active
) {
}
