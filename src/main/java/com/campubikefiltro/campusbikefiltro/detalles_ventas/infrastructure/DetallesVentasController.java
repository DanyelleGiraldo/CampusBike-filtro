package com.campubikefiltro.campusbikefiltro.detalles_ventas.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campubikefiltro.campusbikefiltro.detalles_ventas.application.DetallesVentasServiceImpl;
import com.campubikefiltro.campusbikefiltro.detalles_ventas.domain.entity.DetallesVentas;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/detallesVentas")
public class DetallesVentasController {

    @Autowired
    private DetallesVentasServiceImpl detallesVentasServiceImpl;

    @GetMapping
    public List<DetallesVentas> listAllSalesDetails() {
        return detallesVentasServiceImpl.findAll();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @PostMapping
    public ResponseEntity<?> createSaleDetails(@Valid @RequestBody DetallesVentas detallesVentas, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        detallesVentasServiceImpl.save(detallesVentas);
        return ResponseEntity.status(HttpStatus.CREATED).body("Sale details created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSaleDetails(@PathVariable Long id, @Valid @RequestBody DetallesVentas updatedDetallesVentas, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        try {
            Optional<DetallesVentas> existingVenta = detallesVentasServiceImpl.findById(id);
            if (existingVenta.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sale details not found with id: " + id);
            }

            detallesVentasServiceImpl.update(id, updatedDetallesVentas);
            return ResponseEntity.ok("Sale details updated successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating sale details: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSaleDetails(@PathVariable Long id) {
        try {
            Optional<DetallesVentas> detallesVentas = detallesVentasServiceImpl.findById(id);
            if (detallesVentas.isPresent()) {
                detallesVentasServiceImpl.delete(detallesVentas.get());
                return ResponseEntity.ok("Sale details deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sale details not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting sale details: " + e.getMessage());
        }
    }
}
