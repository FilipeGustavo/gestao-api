package br.com.gestao.api.common.exception;

public record FieldErrorResponse(String field, String message) {
}
