package Daniela.TodoGamersShop.Controllers;

import Daniela.TodoGamersShop.Entities.Generos;
import Daniela.TodoGamersShop.Entities.Juegos;
import Daniela.TodoGamersShop.Entities.Plataformas;
import Daniela.TodoGamersShop.Repositories.GenerosRepository;
import Daniela.TodoGamersShop.Repositories.JuegosRepositorio;
import Daniela.TodoGamersShop.Repositories.PlataformasRepositorio;
import Daniela.TodoGamersShop.Services.AlmacenServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController{

                  // VARIABLES A USAR
    @Autowired
    private JuegosRepositorio juegosRepositorio;

    @Autowired
    private GenerosRepository generosRepository;

    @Autowired
    private PlataformasRepositorio plataformasRepositorio;

    @Autowired
    private AlmacenServicioImpl servicio;


                 // PESTAÑAS DE BUSCADOR

    // pestaña inicio con obtención de algunos juegos
    @GetMapping("")
    public ModelAndView verPaginaDeInicio(@PageableDefault(sort = "titulo", size = 5) Pageable pageable){
        Page <Juegos> juegos = juegosRepositorio.findAll(pageable);
        return new ModelAndView("admin/index")
                .addObject("juegos", juegos);
    }


    // formulario de registro de juegos
    @GetMapping("/juegos/nuevo")
    public ModelAndView mostrarFormularioDeRegistro(){
        List <Generos> generos = generosRepository.findAll(Sort.by("titulo"));
        List <Plataformas> plataformas = plataformasRepositorio.findAll(Sort.by("titulo"));

        return new ModelAndView("admin/nuevo-juego")
                // ver si es juegos en plural o singular
                .addObject("juego", new Juegos())
                .addObject("generos", generos)
                .addObject("plataformas", plataformas);
    }


    // registro de juegos
    @PostMapping("/juegos/nuevo")
    public ModelAndView registrarJuegos(@Validated Juegos juego, BindingResult bindingResult){

        // comprobación de que no tenga errores ni este vacio
        if (bindingResult.hasErrors() || juego.getPortada().isEmpty()){
            bindingResult.rejectValue("portada", "MultiparNotEmpty");

        } else {
            List <Generos> generos = generosRepository.findAll(Sort.by("titulo"));
            List <Plataformas> plataformas = plataformasRepositorio.findAll(Sort.by("titulo"));

            return new ModelAndView("admin/nuevo-juego")
                    // ver si es juegos en plural o singular
                    .addObject("juego", juego)
                    .addObject("generos", generos)
                    .addObject("plataformas", plataformas);
        }

        String rutaPortada = servicio.almacenarArchivo(juego.getPortada());
        juego.setRutaPortada(rutaPortada);

        juegosRepositorio.save(juego);
        return new ModelAndView("redirect:/admin");
    }

    // formulario de modificacion de registro de juegos
    @GetMapping("/juegos/{id}/editar")
    public ModelAndView mostrarFormularioDeModificacionDelJuego(@PathVariable Integer id){
        Juegos juego = juegosRepositorio.getOne(id);
        List <Generos> generos = generosRepository.findAll(Sort.by("titulo"));
        List <Plataformas> plataformas = plataformasRepositorio.findAll(Sort.by("titulo"));

        return new ModelAndView("admin/editar-juegos")
                .addObject("juego", juego)
                .addObject("generos", generos)
                .addObject("plataformas", plataformas);
    }

    // modificacion de registro de juegos
    @PostMapping("/juegos/{id}/editar")
    public ModelAndView actualizarJuego(@PathVariable Integer id, @Validated Juegos juego, BindingResult bindingResult){

        // comprobación de errores
        if (bindingResult.hasErrors()){
            List <Generos> generos = generosRepository.findAll(Sort.by("titulo"));
            List <Plataformas> plataformas = plataformasRepositorio.findAll(Sort.by("titulo"));

            return new ModelAndView("admin/editar-juegos")
                    .addObject("juego", juego)
                    .addObject("generos", generos)
                    .addObject("plataformas", plataformas);
        }

        Juegos juegoDB = juegosRepositorio.getOne(id);
        juegoDB.setTitulo(juego.getTitulo());
        juegoDB.setDescripcion(juego.getDescripcion());
        juegoDB.setPersonajes(juego.getPersonajes());
        juegoDB.setFechaEstreno(juego.getFechaEstreno());
        juegoDB.setYoutubeTrailerId(juego.getYoutubeTrailerId());
        juegoDB.setGeneros(juego.getGeneros());
        juegoDB.setPlataformas(juego.getPlataformas());

        // juego con portada
        if (!juego.getPortada().isEmpty()){
            servicio.eliminarArchivo(juegoDB.getRutaPortada());
            String rutaPortada = servicio.almacenarArchivo(juego.getPortada());
            juegoDB.setRutaPortada(rutaPortada);
        }

        juegosRepositorio.save(juegoDB);
        return new ModelAndView("redirect:/admin");
    }

    // eliminar juego
    @PostMapping("/juegos/{id}/eliminar")
    public String eliminarJuego(@PathVariable Integer id){
        Juegos juego = juegosRepositorio.getOne(id);
        juegosRepositorio.delete(juego);
        servicio.eliminarArchivo(juego.getRutaPortada());

        return "redirect:/admin";
    }

}
