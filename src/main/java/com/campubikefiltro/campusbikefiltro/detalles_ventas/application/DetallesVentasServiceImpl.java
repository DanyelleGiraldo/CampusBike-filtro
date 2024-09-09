package com.campubikefiltro.campusbikefiltro.detalles_ventas.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campubikefiltro.campusbikefiltro.detalles_ventas.domain.entity.DetallesVentas;
import com.campubikefiltro.campusbikefiltro.detalles_ventas.domain.service.DetallesVentasInterface;
import com.campubikefiltro.campusbikefiltro.detalles_ventas.infrastructure.DetallesVentasRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class DetallesVentasServiceImpl implements DetallesVentasInterface {
    @Autowired
    DetallesVentasRepository detallesVentasRepository;

    @Override
    @Transactional
    public void save(DetallesVentas detallesVentas ) {
        detallesVentasRepository.save(detallesVentas);
    }

    @Override
    @Transactional
    public void delete(DetallesVentas detallesVentas ) {
        detallesVentasRepository.delete(detallesVentas);
    }

    @Override
    @Transactional
    public void update(Long id, DetallesVentas detallesVentas) {
        Optional<DetallesVentas> existingVenta = detallesVentasRepository.findById(detallesVentas.getId());

        if (existingVenta.isPresent()){
            DetallesVentas foundventa= existingVenta.get();

            foundventa.setBicicleta(detallesVentas.getBicicleta());
            foundventa.setCantidad(detallesVentas.getCantidad());
            foundventa.setPrecioUnitario(detallesVentas.getPrecioUnitario());
            foundventa.setVenta(detallesVentas.getVenta());
            
            detallesVentasRepository.save(foundventa);

        } else {
        throw new EntityNotFoundException("Sale not found with id: " + id);
        }
    }

    @Override
    @Transactional
    public List<DetallesVentas> findAll() {
        return detallesVentasRepository.findAll();
    }

    @Override
    public Optional<DetallesVentas> findById(Long id) {
        return detallesVentasRepository.findById(id);
    }
}
