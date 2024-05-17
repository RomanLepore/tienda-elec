package com.example.carritoservices.repository;

import com.example.carritoservices.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarritoRepository extends JpaRepository<Carrito,Long> {
}
