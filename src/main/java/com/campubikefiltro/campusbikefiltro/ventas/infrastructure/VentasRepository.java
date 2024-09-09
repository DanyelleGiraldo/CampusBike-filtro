package com.campubikefiltro.campusbikefiltro.ventas.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campubikefiltro.campusbikefiltro.ventas.domain.entity.Ventas;

public interface  VentasRepository extends JpaRepository<Ventas, Long>{

}
