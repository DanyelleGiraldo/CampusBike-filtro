package com.campubikefiltro.campusbikefiltro.bicicletas.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campubikefiltro.campusbikefiltro.bicicletas.domain.entity.Bicicleta;


public interface  bicicletaRepository extends JpaRepository<Bicicleta, Long>{

}
