package com.tecninf.commesse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tecninf.commesse.model.ESkill;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "skill_name")
    @Enumerated(EnumType.STRING)
    private ESkill skillName;

    @ManyToMany(mappedBy = "skills")
    @JsonIgnore
    private List<Dipendenti> dipendenti;

}
