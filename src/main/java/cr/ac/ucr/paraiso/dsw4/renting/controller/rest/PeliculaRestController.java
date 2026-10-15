package cr.ac.ucr.paraiso.dsw4.renting.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import cr.ac.ucr.paraiso.dsw4.renting.business.PeliculaBusiness;
import cr.ac.ucr.paraiso.dsw4.renting.data.PeliculaData;
import cr.ac.ucr.paraiso.dsw4.renting.domain.Pelicula;
import cr.ac.ucr.paraiso.dsw4.renting.dto.PeliculaCreationDTO;
import cr.ac.ucr.paraiso.dsw4.renting.mappers.PeliculaMapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value ="/peliculas")
@CrossOrigin(origins= "http://localhost:4200")
public class PeliculaRestController {
    private final PeliculaBusiness peliculaBusiness;
    private final PeliculaMapper peliculaMapper;

    public PeliculaRestController(PeliculaBusiness peliculaBusiness, PeliculaMapper peliculaMapper) {
        this.peliculaBusiness = peliculaBusiness;
        this.peliculaMapper = peliculaMapper;
    }

    @GetMapping
    public ResponseEntity<List<Pelicula>> findMovies(@RequestParam("titulo") String titulo, @RequestParam("genero") String genero) {
        List<Pelicula> peliculas = peliculaBusiness.findMoviesByTitleOrGenre(titulo, genero);

        if (peliculas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(peliculas);
    }

    @PostMapping
    public ResponseEntity<?> createPelicula(@Validated @RequestBody PeliculaCreationDTO peliculaDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            List<String> errorMessages = errors.stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }

        try {
            Pelicula pelicula = peliculaBusiness.save(peliculaMapper.toPelicula(peliculaDTO));
            return new ResponseEntity<>(pelicula, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(List.of(e.getMessage()));
        }
    }
}