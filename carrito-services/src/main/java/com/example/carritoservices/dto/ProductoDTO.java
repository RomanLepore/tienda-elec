package com.example.carritoservices.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class ProductoDTO {

    private Long id_prod;
    private String nombre;
    private Double precio;
}
