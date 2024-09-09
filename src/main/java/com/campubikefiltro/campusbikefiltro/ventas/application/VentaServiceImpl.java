package com.campubikefiltro.campusbikefiltro.ventas.application;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campubikefiltro.campusbikefiltro.ventas.domain.entity.Ventas;
import com.campubikefiltro.campusbikefiltro.ventas.domain.service.VentaInteface;
import com.campubikefiltro.campusbikefiltro.ventas.infrastructure.VentasRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class VentaServiceImpl implements VentaInteface {
    @Autowired
    VentasRepository ventasRepository;

    @Override
    @Transactional
    public void save(Ventas ventas ) {
        ventasRepository.save(ventas);
    }

    @Override
    @Transactional
    public void delete(Ventas ventas ) {
        ventasRepository.delete(ventas);
    }

    @Override
    @Transactional
    public void update(Long id, Ventas ventas) {
        Optional<Ventas> existingVenta = ventasRepository.findById(ventas.getId());

        if (existingVenta.isPresent()){
            Ventas foundventa= existingVenta.get();

            foundventa.setCliente(ventas.getCliente());
            foundventa.setFecha(LocalDateTime.now());
            
            ventasRepository.save(foundventa);

        } else {
        throw new EntityNotFoundException("Sale not found with id: " + id);
        }
    }

    @Override
    @Transactional
    public List<Ventas> findAll() {
        return ventasRepository.findAll();
    }

    @Override
    public Optional<Ventas> findById(Long id) {
        return ventasRepository.findById(id);
    }   
}
