package com.example.productoservices.excepciones;

public class CarritoNotFoundException extends RuntimeException{
    public CarritoNotFoundException(String mensaje){
        super(mensaje);
    }
}
