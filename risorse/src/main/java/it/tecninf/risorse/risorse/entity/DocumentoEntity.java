package it.tecninf.risorse.risorse.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "DOCUMENTO")
@Data
public class DocumentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String tipo;

    @Column
    private LocalDate scadenza;

    @Lob
    private byte[] file;
}
