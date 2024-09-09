package com.campubikefiltro.campusbikefiltro.ventas.infrastructure;

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

import com.campubikefiltro.campusbikefiltro.ventas.application.VentaServiceImpl;
import com.campubikefiltro.campusbikefiltro.ventas.domain.entity.Ventas;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ventas")
public class VentasController {
    @Autowired
    private VentaServiceImpl ventaServiceImpl;

    @GetMapping
    public List<Ventas> listAllVentas(){
        return ventaServiceImpl.findAll();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @PostMapping
    public ResponseEntity<?> createVenta(@Valid @RequestBody Ventas venta, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        ventaServiceImpl.save(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body("Venta created succesfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatedVenta(@PathVariable Long id, @Valid Ventas updatedVenta, BindingResult result){
        if (result.hasErrors()) {
            return validation(result);
        }
        try {
            Optional<Ventas> exstingVentaOptional = ventaServiceImpl.findById(id);
            if(exstingVentaOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta not found with id: " + id);
            }

            ventaServiceImpl.update(id, updatedVenta);
            return ResponseEntity.ok("Venta updated successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating venta: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable Long id) {
        try {
            Optional<Ventas> venta = ventaServiceImpl.findAll().stream().filter(s -> s.getId().equals(id)).findFirst();
            if (venta.isPresent()) {
                ventaServiceImpl.delete(venta.get());
                return ResponseEntity.ok("venta deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("venta not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting venta: " + e.getMessage());
        }
    }
}
