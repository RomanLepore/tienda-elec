package com.example.ventasservices.controller;

import com.example.ventasservices.dto.CarritoDTO;
import com.example.ventasservices.model.Venta;
import com.example.ventasservices.services.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    private IVentaService ventaServ;

    @PostMapping("/create")
    public void createVenta(@RequestBody Venta venta){
        ventaServ.createVenta(venta);
    }

    @GetMapping("/datos/{id_venta}")
    public CarritoDTO getDatos(@PathVariable Long id_venta){
       return ventaServ.getDatos(id_venta);
    }
}
