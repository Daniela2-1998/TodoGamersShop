package Daniela.TodoGamersShop.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Plataformas {

    @Id
    @Column(name = "id_plataformas")
    private Integer id;

    private String titulo;

}
