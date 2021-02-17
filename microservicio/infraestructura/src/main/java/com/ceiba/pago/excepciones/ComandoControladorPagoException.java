package com.ceiba.pago.excepciones;

public class ComandoControladorPagoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ComandoControladorPagoException(String message) {
        super(message);
    }
}
