package com.example.productoservices.controller;

import com.example.productoservices.model.Producto;
import com.example.productoservices.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prod")
public class ProductoController {
    @Autowired
    private IProductoService prodService;

    @GetMapping("/todos")
    public List<Producto> getListaProductos(){
        return prodService.getListaProductos();
    }

    @GetMapping("/id/{id_prod}")
    public Producto getProducto(@PathVariable Long id_prod){
        return prodService.getProducto(id_prod);
    }
}
