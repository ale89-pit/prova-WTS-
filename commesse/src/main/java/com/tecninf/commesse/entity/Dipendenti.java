//package com.tecninf.commesse.entity;
//
//import com.tecninf.commesse.model.ESeniority;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//public class Dipendenti {
//
//    @Id
//    private String id;
//
//    @Column
//    private String nome;
//
//    @Column
//    private String cognome;
//
//    @Column
//    private LocalDate dataNascita;
//
//    @Column
//    private ESeniority seniority;
//
//    @Column
//    private Double costoGiornaliero;
//
//    @Column
//    @ManyToMany
//    @JoinTable(
//            name = "dipendenti_skills",
//            joinColumns = @JoinColumn(name = "dipendente_id"),
//            inverseJoinColumns = @JoinColumn(name = "skill_id")
//    )
//    private List<Skill> skills;
//}
