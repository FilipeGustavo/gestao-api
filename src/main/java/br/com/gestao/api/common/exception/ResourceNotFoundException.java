package br.com.gestao.api.common.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, Object id) {
        super("%s nao encontrado para o identificador %s".formatted(resourceName, id));
    }
}
