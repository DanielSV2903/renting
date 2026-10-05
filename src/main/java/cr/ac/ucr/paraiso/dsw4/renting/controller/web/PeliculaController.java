package cr.ac.ucr.paraiso.dsw4.renting.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cr.ac.ucr.paraiso.dsw4.renting.business.PeliculaBusiness;
import cr.ac.ucr.paraiso.dsw4.renting.domain.Pelicula;


@Controller
public class PeliculaController {
@Autowired
    private PeliculaBusiness peliculaBusiness;

    @RequestMapping(value="/findMovies", method=RequestMethod.GET)
    public String iniciar(Model model){
        return "findMovies"; //corresponde al nombre de la plantilla
    }
    @RequestMapping(value="/findMovies", method=RequestMethod.POST)
    public String findMovies(Model model, @RequestParam("titulo") String titulo, 
    @RequestParam("genero") String genero){
        List<Pelicula> peliculas = peliculaBusiness.findMoviesByTitleOrGenre(titulo, genero);
        model.addAttribute("peliculas",peliculas);
        return "findMovies"; //corresponde al nombre de la plantilla
    }
    
    

}
