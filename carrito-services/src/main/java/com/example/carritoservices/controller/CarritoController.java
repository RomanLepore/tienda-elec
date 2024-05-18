package com.example.carritoservices.controller;

import com.example.carritoservices.model.Carrito;
import com.example.carritoservices.services.CarritoService;
import com.netflix.discovery.converters.Auto;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {
    @Autowired
    private CarritoService carritoServ;

    @GetMapping("/getTodos")
    public List<Carrito> getCarritos(){
       return carritoServ.getCarritos();
    }
    @PostMapping("/create")
    public void createCarrito(@RequestBody Carrito carrito){
        carritoServ.createCarrito(carrito);
    }

    @GetMapping("/get/{id_carrito}")
    public Carrito getCarrito(@PathVariable Long id_carrito){
        return carritoServ.findById(id_carrito);
    }

    @PostMapping("/addProd/{id_carrito}/{id_prod}")
    public void addProduct(@PathVariable Long id_carrito, @PathVariable Long id_prod){
        carritoServ.addProduct(id_carrito,id_prod);
    }

    @DeleteMapping("/deleteProd/{id_carrito}/{id_prod}")
    public void deleteProduct(@PathVariable Long id_carrito, @PathVariable Long id_prod){
        carritoServ.deleteProduct(id_carrito,id_prod);
    }

}
