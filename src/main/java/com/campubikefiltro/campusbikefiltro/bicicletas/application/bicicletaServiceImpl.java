package com.campubikefiltro.campusbikefiltro.bicicletas.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campubikefiltro.campusbikefiltro.bicicletas.domain.entity.Bicicleta;
import com.campubikefiltro.campusbikefiltro.bicicletas.domain.service.bicicletasinteface;
import com.campubikefiltro.campusbikefiltro.bicicletas.infrastructure.bicicletaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class bicicletaServiceImpl implements bicicletasinteface {
    @Autowired
    bicicletaRepository bicicletaRepository;

    @Override
    @Transactional
    public void save(Bicicleta bicicleta ) {
        bicicletaRepository.save(bicicleta);
    }

    @Override
    @Transactional
    public void delete(Bicicleta bicicleta ) {
        bicicletaRepository.delete(bicicleta);
    }

    @Override
    @Transactional
    public void update(Long id, Bicicleta bicicleta) {
        Optional<Bicicleta> existingBicicleta = bicicletaRepository.findById(bicicleta.getId());

        if (existingBicicleta.isPresent()){
            Bicicleta foundbicicleta = existingBicicleta.get();

            foundbicicleta.setMarca(bicicleta.getMarca()); 
            foundbicicleta.setModelo(bicicleta.getModelo());           
            foundbicicleta.setPrecio(bicicleta.getPrecio());
            foundbicicleta.setStock(bicicleta.getStock());
            bicicletaRepository.save(bicicleta);

        } else {
        throw new EntityNotFoundException("Bike not found with id: " + id);
        }
    }

    @Override
    @Transactional
    public List<Bicicleta> findAll() {
        return bicicletaRepository.findAll();
    }

    @Override
    public Optional<Bicicleta> findById(Long id) {
        return bicicletaRepository.findById(id);
    }
}
