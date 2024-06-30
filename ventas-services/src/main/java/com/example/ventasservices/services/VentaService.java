package com.example.ventasservices.services;

import com.example.ventasservices.dto.CarritoDTO;
import com.example.ventasservices.excepciones.VentaNotFoundException;
import com.example.ventasservices.model.Venta;
import com.example.ventasservices.repository.ICarritoAPI;
import com.example.ventasservices.repository.IVentaRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService{
    @Autowired
    private IVentaRepository vtaRepo;

    @Autowired
    private ICarritoAPI carritoAPI;

    @Override
    public void createVenta(Venta venta) {
        vtaRepo.save(venta);
    }

    @Override
    @CircuitBreaker(name = "carrito-services", fallbackMethod = "fallbackGetDatos")
    @Retry(name="carrito-services")
    public CarritoDTO getDatos(Long id_venta) {

        Venta venta = this.getVenta(id_venta);

        CarritoDTO carrito = carritoAPI.getCarrito(venta.getId_carrito());

        CarritoDTO carritoVacio = new CarritoDTO();

        carritoVacio.setId_carrito(carrito.getId_carrito());
        carritoVacio.setTotal(carrito.getTotal());
        carritoVacio.setNombreProductos(carritoAPI.getNombreProd(venta.getId_carrito()));

        return carritoVacio;
    }

    private Venta getVenta(Long id_venta) {
        return vtaRepo.findById(id_venta).orElseThrow(()-> new VentaNotFoundException("No existe dicha venta"));
    }

    public CarritoDTO fallbackGetDatos(Throwable throwable){
        return new CarritoDTO(null, null, null);
    }

}
