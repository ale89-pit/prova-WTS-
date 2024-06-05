package com.tecninf.commesse.entity;


import com.tecninf.commesse.model.RagioneSociale;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CLIENTI")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clienti {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(length = 100)
    String nome;

    @Column
    @Email(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    String email;

    @Column(name = "ragione_sociale")
    @Enumerated(EnumType.STRING)
    RagioneSociale ragioneSociale;

    @Column(name = "partita_iva",unique = true,length = 11)
    String partitaIva;

    @Column(name = "indirizzo")
    String indirizzo;

    @Column
    String telefono;

    //sdi
}
