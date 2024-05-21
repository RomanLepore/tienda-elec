package com.example.ventasservices.services;
import com.example.ventasservices.dto.CarritoDTO;
import com.example.ventasservices.model.Venta;

public interface IVentaService {
    public void createVenta(Venta venta);
    public CarritoDTO getDatos(Long id_venta);

}
