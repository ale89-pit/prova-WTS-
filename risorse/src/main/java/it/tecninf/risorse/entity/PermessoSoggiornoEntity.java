package it.tecninf.risorse.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "PERMESSO_SOGGIORNO")
@Data
public class PermessoSoggiornoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String tipo;

    @Column
    private LocalDate scadenza;

    @Lob
    private byte[] file;
}
