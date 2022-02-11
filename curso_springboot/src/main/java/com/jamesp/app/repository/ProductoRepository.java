package com.jamesp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jamesp.app.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
