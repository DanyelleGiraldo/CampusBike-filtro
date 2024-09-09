package com.campubikefiltro.campusbikefiltro.bicicletas.domain.service;


import java.util.List;
import java.util.Optional;

import com.campubikefiltro.campusbikefiltro.bicicletas.domain.entity.Bicicleta;

public interface bicicletasinteface {
    void save(Bicicleta bicicleta);
    void delete(Bicicleta bicicleta);
    void update(Long id, Bicicleta bicicleta);
    List<Bicicleta> findAll();
    Optional<Bicicleta> findById(Long id);

}
