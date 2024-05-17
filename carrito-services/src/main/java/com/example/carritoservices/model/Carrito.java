package com.example.carritoservices.model;


import com.example.carritoservices.dto.ProductoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@Entity
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_carrito;
    private Double total;

    // Cada carrito tiene una lista con los id de los productos que contiene. Mapeada y persistida en una tabla separada.
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> listaIdProd;


}
