package com.example.carritoservices.services;

import com.example.carritoservices.dto.ProductoDTO;
import com.example.carritoservices.model.Carrito;
import com.example.carritoservices.repository.ICarritoRepository;
import com.example.carritoservices.repository.IProdAPI;
import com.netflix.discovery.converters.Auto;
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
    public Carrito findById(Long id_carrito) {
        return carritoRepo.findById(id_carrito).orElse(null);
    }

    @Override
    public void createCarrito(Carrito carrito) {
        carritoRepo.save(carrito);
    }

    @Override
    public void deleteCarrito(Long id_carrito) {
        carritoRepo.deleteById(id_carrito);
    }

    @Override
    public void addProduct(Long id_carrito,Long id_prod) {
        // encuentra el carrito por su id
        Carrito carrito = this.findById(id_carrito);
        ProductoDTO producto = this.getProducto(id_prod);
        boolean existe = false;
        Double totalCarrito = carrito.getTotal();

        if(carrito!=null){

            List<Long> idsProductos = carrito.getListaIdProd();

            for (Long listaProd:idsProductos) {

                if(listaProd.equals(id_prod)){
                    existe = true;
                }
            }

            if(!existe){

                Double precio = producto.getPrecio();
                totalCarrito += precio;
                carrito.setTotal(totalCarrito);
                idsProductos.add(id_prod);
                //hacer metodo edit
                this.editCarrito(id_carrito,carrito);
            }
        }

    }

    @Override
    public void deleteProduct(Long id_prod) {
    }

    private ProductoDTO getProducto(Long id_prod) {
        ProductoDTO producto = prodAPI.getProducto(id_prod);
        return producto;
    }

    private void editCarrito(Long id_aeditar, Carrito nuevoCarrito) {
            Carrito carritoAEditar = this.findById(id_aeditar);

           carritoAEditar.setListaIdProd(nuevoCarrito.getListaIdProd());
           carritoAEditar.setTotal(nuevoCarrito.getTotal());

           carritoRepo.save(nuevoCarrito);
    }

}
