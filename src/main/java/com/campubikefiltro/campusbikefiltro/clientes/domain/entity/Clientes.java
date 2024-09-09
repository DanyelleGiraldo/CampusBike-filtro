package com.campubikefiltro.campusbikefiltro.clientes.domain.entity;


import com.campubikefiltro.campusbikefiltro.ciudades.domain.Entity.Ciudades;

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
@Table(name="clientes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Clientes {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="VARCHAR(100)")
    private String nombre;
    
    @Column(columnDefinition="VARCHAR(100)")
    private String email;

    @Column(columnDefinition="bigint")
    private int telefono;

    @Column(columnDefinition="VARCHAR(100)")
    private String password;

    @ManyToOne
    @JoinColumn(name="ciudad_id")
    private Ciudades ciudades;
}
