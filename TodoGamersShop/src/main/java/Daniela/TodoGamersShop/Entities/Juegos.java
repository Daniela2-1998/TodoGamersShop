package Daniela.TodoGamersShop.Entities;

import jdk.vm.ci.meta.Local;
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
    @JoinTable(name = "plataformas-juegos",
            joinColumns = @JoinColumn(name = "id_juegos"),
            inverseJoinColumns = @JoinColumn(name = "id_plataformas"))
    private List <Plataformas> plataformas;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "plataformas-juegos",
            joinColumns = @JoinColumn(name = "id_juegos"),
            inverseJoinColumns = @JoinColumn(name = "id_generos"))
    private List <Generos> generos;

    @Transient
    private MultipartFile portada;

}
