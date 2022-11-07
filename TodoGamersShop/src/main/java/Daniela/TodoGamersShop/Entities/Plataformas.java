package Daniela.TodoGamersShop.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Plataformas {

                          // VARIABLES
    @Id
    @Column(name = "id_plataformas")
    private Integer id;

    private String titulo;

                         // CONSTRUCTORES
    // vacio
    public Plataformas() {
        super();
    }

    // con id
    public Plataformas(Integer id) {
        super();
        this.id = id;
    }

    // con titulo
    public Plataformas(String titulo) {
        super();
        this.titulo = titulo;
    }

    // con id y titulo
    public Plataformas(Integer id, String titulo) {
        super();
        this.id = id;
        this.titulo = titulo;
    }


    // GETTERS Y SETTERS
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }


    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
