package it.tecninf.risorse.risorse.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "ANAGRAFICA")
@Data
public class AnagraficaEntity {

    @Id
    private Long id;

    @Column
    private String nome;
    
    @Column
    private String cognome;

    @Column
    private String codiceFiscale;

    @Column
    private String luogoNascita;

    @Column
    private LocalDate dataNascita;

    @Column
    private String residenza;

    @Column
    private String cellulare;

    @Column
    private String emailPrivata;

    @Column
    private String istruzione;

    @Column
    @OneToOne(fetch = FetchType.LAZY)
    private DocumentoEntity documento;

    @Column
    @OneToOne(fetch = FetchType.LAZY)
    private PermessoSoggiornoEntity contratto;

    @Column
    private String genere;

    @Column
    private String formazione;

    @Column
    private LocalDate visiteMedica;

    @Column
    private String sceltaTFR;
}
