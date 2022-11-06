package Daniela.TodoGamersShop.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Generos {

    @Id
    @Column(name = "id_generos")
    private Integer id;

    private String titulo;
}
