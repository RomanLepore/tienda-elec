package com.example.productoservices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Producto {

    private Long id_prod;
    private String nombre,marca;
    private double precio;
}
