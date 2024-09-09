package com.campubikefiltro.campusbikefiltro.detalles_ventas.domain.entity;


import com.campubikefiltro.campusbikefiltro.bicicletas.domain.entity.Bicicleta;
import com.campubikefiltro.campusbikefiltro.ventas.domain.entity.Ventas;

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
@Table(name="detalles_ventas")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DetallesVentas {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Ventas venta;

    @ManyToOne
    @JoinColumn(name="bicicleta_id")
    private Bicicleta bicicleta;
    
    private int cantidad;

    @Column(columnDefinition="DECIMAL(10,2)")
    private Double precioUnitario;
}
