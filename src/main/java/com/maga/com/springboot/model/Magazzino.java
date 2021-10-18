package com.maga.com.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Entity
public class Magazzino implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_magazzino;
    private String type;
    private String descrizione;

    @OneToMany(mappedBy = "magazzino", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Prodotto> prodotto;

    public List<Prodotto> getProdotto() {
        return prodotto;
    }

    public void setProdotto(List<Prodotto> prodotto) {
        this.prodotto = prodotto;
    }

    public Long getId_magazzino() {
        return id_magazzino;
    }

    public void setId_magazzino(Long id_magazzino) {
        this.id_magazzino = id_magazzino;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
