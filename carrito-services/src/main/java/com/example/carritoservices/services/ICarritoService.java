package com.example.carritoservices.services;

import com.example.carritoservices.dto.ProductoDTO;
import com.example.carritoservices.model.Carrito;

import java.util.List;

public interface ICarritoService {
    public List<Carrito> getCarritos();
    public Carrito getCarrito(Long id_carrito);
    public void createCarrito(Carrito carrito);
    public void deleteCarrito(Long id_carrito);
    public void addProduct(Long id_carrito,Long id_prod);
    public void deleteProduct(Long id_carrito,Long id_prod);
    public List<String> getNombreProd(Long id_carrito);


}
