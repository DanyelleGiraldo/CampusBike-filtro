package com.campubikefiltro.campusbikefiltro.ciudades.domain.Entity;

import com.campubikefiltro.campusbikefiltro.paises.domain.entity.Pais;

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
@Table(name="ciudades")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Ciudades {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition=("varchar(100)"))
    private String nombre;

    @ManyToOne
    @JoinColumn(name="pais_id")
    private Pais pais;
}
