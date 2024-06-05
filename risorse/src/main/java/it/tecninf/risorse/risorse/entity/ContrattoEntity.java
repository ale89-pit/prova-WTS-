package it.tecninf.risorse.risorse.entity;

import it.tecninf.risorse.risorse.utility.EOrario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "CONTRATTO")
@Data
public class ContrattoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String tipoContratto;

    @Column
    private String tipologia;

    @Column
    private LocalDate scadenza;

    @Column
    private EOrario orario;

    @Column
    private Integer livello;

    @Column
    private Boolean buonoPasto;

    @Column
    private Float giornaliero;

    @Column
    private Float mensile;

    @Column
    private Boolean tredicesima;

    @Column
    private String profiloProfessionale;


}
