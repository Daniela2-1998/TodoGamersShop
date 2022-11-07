package Daniela.TodoGamersShop.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Generos {

                      //   VARIABLES
    @Id
    @Column(name = "id_generos")
    private Integer id;

    private String titulo;

                     //  CONSTRUCTORES
    // vacio
    public Generos() {
        super();
    }

    // con titulo
    public Generos(String titulo) {
        super();
        this.titulo = titulo;
    }

    // con id
    public Generos(Integer id) {
        super();
        this.id = id;
    }

    // con id y titulo
    public Generos(Integer id, String titulo) {
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
