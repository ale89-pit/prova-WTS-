package com.tecninf.commesse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commessa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "nome_progetto")
    private String nomeProgetto;

    @ManyToOne
    private Clienti cliente;

    @Column
    private String descrizioneProgetto;

    @Column
    private LocalDate dataInizio;

    @Column
    private LocalDate dataFine;

    @Column
    private String responsabileProgetto;

    @Column(name="skill_richieste")
    @OneToMany
    private List<Skill> skillRichieste;

    @OneToMany(mappedBy = "commessa")
    @JsonIgnore
    private List<AssegnazioneCommesse> assegnazioni;

    @Column(name="budget")
    private Double budget;

    @Column
    private Boolean statusAssegnazione;





}
