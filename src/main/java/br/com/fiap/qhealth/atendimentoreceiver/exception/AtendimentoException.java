package br.com.fiap.qhealth.atendimentoreceiver.exception;

public class AtendimentoException extends RuntimeException {
    public AtendimentoException(String message) {
        super(message);
    }

    public AtendimentoException(String message, Throwable cause) {
        super(message, cause);
    }
}

