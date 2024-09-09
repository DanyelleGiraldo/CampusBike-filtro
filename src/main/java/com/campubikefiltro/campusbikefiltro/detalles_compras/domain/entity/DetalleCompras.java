package com.campubikefiltro.campusbikefiltro.detalles_compras.domain.entity;


import com.campubikefiltro.campusbikefiltro.compras.domain.Compras;
import com.campubikefiltro.campusbikefiltro.repuestos.domain.entity.Repuestos;

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
@Table(name="detalles_compras")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DetalleCompras {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="repuesto_id")
    private Repuestos repuestos;

    @ManyToOne
    @JoinColumn(name="compra_id")
    private Compras compras;

    private int cantidad;

    @Column(columnDefinition = "Decimal(10,2)")
    private Double precio_unitario;

    
}
