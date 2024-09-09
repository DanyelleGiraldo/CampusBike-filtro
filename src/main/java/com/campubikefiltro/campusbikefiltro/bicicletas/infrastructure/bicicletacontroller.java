package com.campubikefiltro.campusbikefiltro.bicicletas.infrastructure;

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

import com.campubikefiltro.campusbikefiltro.bicicletas.application.bicicletaServiceImpl;
import com.campubikefiltro.campusbikefiltro.bicicletas.domain.entity.Bicicleta;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/Bicicleta")
public class bicicletacontroller {

    @Autowired
    private bicicletaServiceImpl bicicletaserviceImpl;

    @GetMapping
    public List<Bicicleta> listAllBikes(){
        return bicicletaserviceImpl.findAll();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @PostMapping
    public ResponseEntity<?> createBike(@Valid @RequestBody Bicicleta bicicleta, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        bicicletaserviceImpl.save(bicicleta);
        return ResponseEntity.status(HttpStatus.CREATED).body("bike created succesfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatedBike(@PathVariable Long id, @Valid Bicicleta updatedBicicleta, BindingResult result){
        if (result.hasErrors()) {
            return validation(result);
        }
        try {
            Optional<Bicicleta> existionBikeOptional = bicicletaserviceImpl.findById(id);
            if(existionBikeOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bike not found with id: " + id);
            }

            bicicletaserviceImpl.update(id, updatedBicicleta);
            return ResponseEntity.ok("Bike updated successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating Bike: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBike(@PathVariable Long id) {
        try {
            Optional<Bicicleta> bicicleta = bicicletaserviceImpl.findAll().stream().filter(s -> s.getId().equals(id)).findFirst();
            if (bicicleta.isPresent()) {
                bicicletaserviceImpl.delete(bicicleta.get());
                return ResponseEntity.ok("Bike deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bike not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting Bike: " + e.getMessage());
        }
    }
}

