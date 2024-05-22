package com.example.carritoservices.services;

import com.example.carritoservices.dto.ProductoDTO;
import com.example.carritoservices.model.Carrito;
import com.example.carritoservices.repository.ICarritoRepository;
import com.example.carritoservices.repository.IProdAPI;
import com.example.carritoservices.excepciones.CarritoNotFoundException;
import com.netflix.discovery.converters.Auto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoService implements ICarritoService{
    @Autowired
    private ICarritoRepository carritoRepo;
    @Autowired
    private IProdAPI prodAPI;

    @Override
    public List<Carrito> getCarritos() {
        return carritoRepo.findAll();
    }

    @Override
    public Carrito getCarrito(Long id_carrito) {
        return carritoRepo.findById(id_carrito).orElseThrow(()-> new CarritoNotFoundException("No existe dicho carrito"));
    }

    @Override
    public void createCarrito(Carrito carrito) {

        Carrito carritoVacio = new Carrito();
        List<Long> listaId = carrito.getListaIdProd();
        List<ProductoDTO> listaProductos = prodAPI.getListaProductos();
        double precio = 0;

        for (Long idProd : listaId) {
            for (ProductoDTO producto : listaProductos) {
                if (producto.getId_prod().equals(idProd)) {
                    precio += producto.getPrecio();
                }
            }
        }


        carritoVacio.setTotal(precio);
        carritoVacio.setListaIdProd(carrito.getListaIdProd());
        carritoRepo.save(carritoVacio);

    }

    @Override
    public void deleteCarrito(Long id_carrito) {
        carritoRepo.deleteById(id_carrito);
    }

    @Override
    @CircuitBreaker(name = "producto-services", fallbackMethod = "fallbackAddProduct")
    @Retry(name="producto-services")
    public void addProduct(Long id_carrito,Long id_prod) {

        // busca el carrito por su id
        Carrito carrito = this.getCarrito(id_carrito);


        // busca el producto por su id
        ProductoDTO producto = this.getProducto(id_prod);


        List<Long> idsProductos = carrito.getListaIdProd();

        Double precio = producto.getPrecio();
        carrito.setTotal(carrito.getTotal() + precio);


        idsProductos.add(id_prod);

        // Actualiza el carrito en el repositorio
        this.editCarrito(id_carrito, carrito);

    }

    @Override
    public void deleteProduct(Long id_carrito,Long id_prod) {
        Carrito carrito = this.getCarrito(id_carrito);

        ProductoDTO producto = this.getProducto(id_prod);

        List<Long> listaIds = carrito.getListaIdProd();

        Double precio = producto.getPrecio();
        carrito.setTotal(carrito.getTotal() - precio);


        listaIds.remove(id_prod);

        // Actualiza el carrito en el repositorio
        this.editCarrito(id_carrito, carrito);

    }

    private ProductoDTO getProducto(Long id_prod) {
        ProductoDTO producto = prodAPI.getProducto(id_prod);
        return producto;
    }

    private void editCarrito(Long id_aeditar, Carrito nuevoCarrito) {
            Carrito carritoAEditar = this.getCarrito(id_aeditar);

           carritoAEditar.setListaIdProd(nuevoCarrito.getListaIdProd());
           carritoAEditar.setTotal(nuevoCarrito.getTotal());

           carritoRepo.save(nuevoCarrito);
    }

    private List<Long> getProductos(Long id_carrito){
        Carrito carrito = this.getCarrito(id_carrito);
        List<Long> listaId = carrito.getListaIdProd();

        return listaId;
    }


    public List<String> getNombreProd(Long id_carrito) {
        List<Long> listaId = this.getProductos(id_carrito);
        List<ProductoDTO> listaProductos = prodAPI.getListaProductos();
        List<String> listaNombres = new ArrayList<>();

        for (Long idProd : listaId) {
            for (ProductoDTO producto : listaProductos) {
                if (producto.getId_prod().equals(idProd)) {
                    listaNombres.add(producto.getNombre());
                }
            }
        }

        return listaNombres;
    }

    public ProductoDTO fallbackAddProduct(Throwable throwable){
            return new ProductoDTO(999999L, "ERROR", null);
    }

}
