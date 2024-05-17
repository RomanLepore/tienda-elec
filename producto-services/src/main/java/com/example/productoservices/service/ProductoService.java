package com.example.productoservices.service;

import com.example.productoservices.model.Producto;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService implements IProductoService{


     private List<Producto> listaProductos = new ArrayList<Producto>();

     // al iniciar carga lista de productos emulando bd
     @PostConstruct
     public void init(){
         this.loadProductos();
     }


    @Override
    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    @Override
    public Producto getProducto(Long id_prod) {
        for (Producto lista:listaProductos) {
            if (lista.getId_prod() == id_prod){
               return lista;
            }
        }
        return null;
    }

    private void loadProductos(){
        listaProductos.add(new Producto(1L,"Microondas","Random", 3000.0));
        listaProductos.add(new Producto(2L, "Lavadora", "Lavamatic", 4500.0));
        listaProductos.add(new Producto(3L, "Aspiradora", "SucciónMax", 1200.0));
        listaProductos.add(new Producto(4L, "Nevera", "FrioPlus", 6000.0));
        listaProductos.add(new Producto(5L, "Licuadora", "MixMaster", 800.0));
        listaProductos.add(new Producto(6L, "Cafetera", "AromaCafé", 1500.0));
        listaProductos.add(new Producto(7L, "Tostadora", "CrispyToast", 700.0));
        listaProductos.add(new Producto(8L, "Batidora", "PowerBlend", 2000.0));
        listaProductos.add(new Producto(9L, "Horno eléctrico", "HeatMaster", 3500.0));
        listaProductos.add(new Producto(10L, "Exprimidor de jugos", "JuicySqueeze", 1000.0));
    }
}
