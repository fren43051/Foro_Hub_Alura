package com.aluracursos.foro_hub.config;

public class DuplicateTopicException extends RuntimeException {
    public DuplicateTopicException(String message) {
        super(message);
    }
}

