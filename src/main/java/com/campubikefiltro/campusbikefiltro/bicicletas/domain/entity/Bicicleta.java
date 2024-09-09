package com.campubikefiltro.campusbikefiltro.bicicletas.domain.entity;

import com.campubikefiltro.campusbikefiltro.marca.domain.entity.Marca;
import com.campubikefiltro.campusbikefiltro.modelo.domain.entity.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="bicicletas")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Bicicleta {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "modelo")
    private Modelo modelo;

    @ManyToOne
    @JoinColumn(name = "marca")
    private Marca marca;

    @Column(columnDefinition="DECIMAL(10,2)")
    private Double precio;

    private int stock;
}
