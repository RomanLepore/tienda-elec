package com.example.carritoservices.repository;

import com.example.carritoservices.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// consumo microservicio producto a traves de API.
@FeignClient(name = "producto-services", url = "http://localhost:8081/prod")
public interface IProdAPI {
    @GetMapping("/todos")
    public List<ProductoDTO> getListaProductos();

    @GetMapping("/id/{id_prod}")
    public ProductoDTO getProducto(@PathVariable ("id_prod") Long id_prod);
}
