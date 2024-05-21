package com.example.ventasservices.excepciones;

public class VentaNotFoundException extends RuntimeException{
    public VentaNotFoundException(String mensaje){
        super(mensaje);
    }
}
