package com.example.banco_api.exception;

public class OperacaoInvalidaException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OperacaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
