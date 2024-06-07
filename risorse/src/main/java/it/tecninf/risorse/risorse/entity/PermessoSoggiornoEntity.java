package it.tecninf.risorse.risorse.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "PERMESSO_SOGGIORNO")
@Data
public class PermessoSoggiornoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String nazionalita;

    @Column
    private LocalDate scadenza;

    @Lob
    private byte[] file;
}
