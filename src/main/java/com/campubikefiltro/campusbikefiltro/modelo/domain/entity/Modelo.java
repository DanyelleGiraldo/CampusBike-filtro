package com.campubikefiltro.campusbikefiltro.modelo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="modelo")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Modelo {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="VARCHAR(100)")
    private String nombre;

    @Column(columnDefinition="VARCHAR(100)")
    private String descripcion;

}
