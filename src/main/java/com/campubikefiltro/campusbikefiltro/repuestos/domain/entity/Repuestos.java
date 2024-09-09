package com.campubikefiltro.campusbikefiltro.repuestos.domain.entity;

import com.campubikefiltro.campusbikefiltro.marca.domain.entity.Marca;
import com.campubikefiltro.campusbikefiltro.modelo.domain.entity.Modelo;
import com.campubikefiltro.campusbikefiltro.proveedores.domain.entity.Proveedores;

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
@Table(name="repuestos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Repuestos {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="VARCHAR(100)")
    private String nombre;

    @Column(columnDefinition="VARCHAR(200)")
    private String descripcion;

    @Column(columnDefinition = "DECIMAL(10,2)")
    private Double precio;

    private int stock;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedores proveedor;

    @ManyToOne
    @JoinColumn(name = "modelo")
    private Modelo modelo;

    @ManyToOne
    @JoinColumn(name = "marca")
    private Marca marca;
}
