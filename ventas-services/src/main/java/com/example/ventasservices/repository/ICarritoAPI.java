package com.example.ventasservices.repository;

import com.example.ventasservices.dto.CarritoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "carrito-services", url = "http://localhost:8082/carrito")
public interface ICarritoAPI {

    @GetMapping("/get/{id_carrito}")
    public CarritoDTO getCarrito(@PathVariable ("id_carrito") Long id_carrito);

    @GetMapping("/listaProd/{id_carrito}")
    public List<String> getNombreProd(@PathVariable ("id_carrito") Long id_carrito);
}
