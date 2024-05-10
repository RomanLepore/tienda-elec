package com.example.productoservices.service;

import com.example.productoservices.model.Producto;

import java.util.List;

public interface IProductoService {
    public List<Producto> getListaProductos();

    public Producto getProducto(Long id_prod);
}
