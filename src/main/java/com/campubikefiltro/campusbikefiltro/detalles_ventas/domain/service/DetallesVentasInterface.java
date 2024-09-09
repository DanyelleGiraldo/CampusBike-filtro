package com.campubikefiltro.campusbikefiltro.detalles_ventas.domain.service;

import java.util.List;
import java.util.Optional;

import com.campubikefiltro.campusbikefiltro.detalles_ventas.domain.entity.DetallesVentas;
public interface DetallesVentasInterface {
    void save(DetallesVentas detallesVentas);
    void delete(DetallesVentas detallesVentas);
    void update(Long id, DetallesVentas detallesVentas);
    List<DetallesVentas> findAll();
    Optional<DetallesVentas> findById(Long id);
}
