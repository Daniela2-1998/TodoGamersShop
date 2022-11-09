package Daniela.TodoGamersShop.Controllers;

import Daniela.TodoGamersShop.Entities.Juegos;
import Daniela.TodoGamersShop.Repositories.JuegosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("")
public class HomeController{

    @Autowired
    private JuegosRepositorio juegosRepositorio;

    // página de inicio
    @GetMapping("")
    public ModelAndView verPaginaDeInicio(){
        List <Juegos> ultimosJuegos = juegosRepositorio.findAll(
                PageRequest.of(
                0,
                4,
                Sort.by("fechaEstreno").descending())).toList();

        return new ModelAndView("index")
                .addObject("ultimosJuegos", ultimosJuegos);
    }


    // listado de juegos
    @GetMapping("/juegos")
    public ModelAndView listarJuegos(@PageableDefault(sort = "fechaEsteno", direction = Sort.Direction.DESC)
                                         Pageable pageable){
        Page <Juegos> juegos = juegosRepositorio.findAll(pageable);
        return new ModelAndView("juegos")
                .addObject("juegos", juegos);
    }

    // mostrar detalles del juego elegido
    @GetMapping("/juegos/{id}")
    public ModelAndView mostrarDetallesDelJuego(@PathVariable Integer id){
        Juegos juego = juegosRepositorio.getOne(id);
        return new ModelAndView("juego")
                .addObject("juego", juego);
    }

}
