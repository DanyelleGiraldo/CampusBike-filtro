package com.campubikefiltro.campusbikefiltro.ventas.domain.service;

import java.util.List;
import java.util.Optional;

import com.campubikefiltro.campusbikefiltro.ventas.domain.entity.Ventas;


public interface VentaInteface {
    void save(Ventas venta);
    void delete(Ventas venta);
    void update(Long id, Ventas venta);
    List<Ventas> findAll();
    Optional<Ventas> findById(Long id);
}
