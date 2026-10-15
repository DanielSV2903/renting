package cr.ac.ucr.paraiso.dsw4.renting.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cr.ac.ucr.paraiso.dsw4.renting.domain.Actor;
import cr.ac.ucr.paraiso.dsw4.renting.domain.Genero;
import cr.ac.ucr.paraiso.dsw4.renting.domain.Pelicula;
import cr.ac.ucr.paraiso.dsw4.renting.dto.PeliculaCreationDTO;

@Component
public class PeliculaMapper {

    public Pelicula toPelicula(PeliculaCreationDTO peliculaCreationDTO) {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo(peliculaCreationDTO.getTitulo());
        pelicula.setSubtitulada(peliculaCreationDTO.isSubtitulada());
        pelicula.setEstreno(peliculaCreationDTO.isEstreno());
        pelicula.setGenero(mapGenero(peliculaCreationDTO.getGenero()));
        pelicula.setActores(mapActors(peliculaCreationDTO.getActores()));
        return pelicula;
    }

    private List<Actor> mapActors(List<PeliculaCreationDTO.ActorDTO> actorDTOs) {
        if (actorDTOs == null) {
            return List.of();
        }
        return actorDTOs.stream()
                .map(actorDTO -> new Actor(actorDTO.getActorId(), null, null))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    private Genero mapGenero(PeliculaCreationDTO.GeneroDTO generoDTO) {
        if (generoDTO == null) {
            return null;
        }
        return new Genero(generoDTO.getGeneroId(), null);
    }
}
