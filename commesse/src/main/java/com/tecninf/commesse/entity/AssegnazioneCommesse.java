package com.tecninf.commesse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ASSEGNAZIONE_COMMESSE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssegnazioneCommesse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "commessa_id", nullable = false)
    private Commessa commessa;

    @Column
    private LocalDate dataInizioCommessaDipendente;

    @Column
    private LocalDate dataFineCommessaDipendente;

    @ManyToOne
    @JoinColumn(name = "dipendente_id", nullable = false)
    private Dipendenti dipendente;

    //Aggiungere centro di costo



}
