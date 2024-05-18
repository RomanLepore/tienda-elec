package com.example.carritoservices.excepciones;

public class CarritoNotFoundException extends RuntimeException{
    public CarritoNotFoundException(String mensaje){
        super(mensaje);
    }
}
