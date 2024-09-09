package com.campubikefiltro.campusbikefiltro.detalles_ventas.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campubikefiltro.campusbikefiltro.detalles_ventas.domain.entity.DetallesVentas;

public interface DetallesVentasRepository extends JpaRepository<DetallesVentas, Long>{

}
