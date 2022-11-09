package Daniela.TodoGamersShop.Entities;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Juegos {

                     //   VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_juegos")
    private Integer id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descripcion;

    @NotBlank
    private String personajes;

    @NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaEstreno;

    @NotBlank
    private String youtubeTrailerId;

    private String rutaPortada;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "plataformas_juegos",
            joinColumns = @JoinColumn(name = "id_juegos"),
            inverseJoinColumns = @JoinColumn(name = "id_plataformas"))
    private List <Plataformas> plataformas;


    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "generos_juegos",
            joinColumns = @JoinColumn(name = "id_juegos"),
            inverseJoinColumns = @JoinColumn(name = "id_generos"))
    private List <Generos> generos;

    @Transient
    private MultipartFile portada;

                     //  CONSTRUCTORES
    // vacio
    public Juegos() {
        super();
    }

    // con generos y plataformas, pero no id
    public Juegos(@NotBlank String titulo, @NotBlank String descripcion, @NotBlank String personajes,
                  @NotBlank LocalDate fechaEstreno, @NotBlank String youtubeTrailerId, String rutaPortada,
                  @NotEmpty List<Plataformas> plataformas, @NotEmpty List<Generos> generos, MultipartFile portada) {
        super();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.personajes = personajes;
        this.fechaEstreno = fechaEstreno;
        this.youtubeTrailerId = youtubeTrailerId;
        this.rutaPortada = rutaPortada;
        this.plataformas = plataformas;
        this.generos = generos;
        this.portada = portada;
    }

    //  con id, generos y plataformas
    public Juegos(Integer id, @NotBlank String titulo, @NotBlank String descripcion, @NotBlank String personajes,
                  @NotBlank LocalDate fechaEstreno, @NotBlank String youtubeTrailerId, String rutaPortada,
                  @NotEmpty List<Plataformas> plataformas, @NotEmpty List<Generos> generos, MultipartFile portada) {
        super();
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.personajes = personajes;
        this.fechaEstreno = fechaEstreno;
        this.youtubeTrailerId = youtubeTrailerId;
        this.rutaPortada = rutaPortada;
        this.plataformas = plataformas;
        this.generos = generos;
        this.portada = portada;
    }

                          //  GETTERS Y SETTERS
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



    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    public String getPersonajes() {
        return personajes;
    }
    public void setPersonajes(String personajes) {
        this.personajes = personajes;
    }



    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }
    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }



    public String getYoutubeTrailerId() {
        return youtubeTrailerId;
    }
    public void setYoutubeTrailerId(String youtubeTrailerId) {
        this.youtubeTrailerId = youtubeTrailerId;
    }



    public String getRutaPortada() {
        return rutaPortada;
    }
    public void setRutaPortada(String rutaPortada) {
        this.rutaPortada = rutaPortada;
    }



    public List<Plataformas> getPlataformas() {
        return plataformas;
    }
    public void setPlataformas(List<Plataformas> plataformas) {
        this.plataformas = plataformas;
    }



    public List<Generos> getGeneros() {
        return generos;
    }
    public void setGeneros(List<Generos> generos) {
        this.generos = generos;
    }



    public MultipartFile getPortada() {
        return portada;
    }
    public void setPortada(MultipartFile portada) {
        this.portada = portada;
    }
}
