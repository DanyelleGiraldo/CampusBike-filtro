package com.campubikefiltro.campusbikefiltro.compras.domain;

import java.time.LocalDateTime;

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
@Table(name="compras")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Compras {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name="proveedor_id")
    private Proveedores proveedores;

    @Column(columnDefinition = "Decimal(10,2)")
    private Double total;
}   
