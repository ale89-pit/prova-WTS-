package it.tecninf.risorse.risorse.entity;

import it.tecninf.risorse.risorse.utility.EGender;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "ANAGRAFICA")
@Data
public class AnagraficaEntity {

    @Id
    private String id;

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
    private String emailAziendale;

    @Column
    private String istruzione;

    @PrimaryKeyJoinColumn
    @OneToOne(fetch = FetchType.LAZY)
    private DocumentoEntity documento;

    @PrimaryKeyJoinColumn
    @OneToOne(fetch = FetchType.LAZY)
    private PermessoSoggiornoEntity permessoSoggiorno;

    @Column
    @Enumerated(EnumType.STRING)
    private EGender genere;

    @Column
    private String formazione;

    @Column
    private LocalDate visiteMedica;

    @PrimaryKeyJoinColumn
    @OneToOne(fetch = FetchType.LAZY)
    private ContrattoEntity contratto;

    @Column
    private String sceltaTFR;
}
