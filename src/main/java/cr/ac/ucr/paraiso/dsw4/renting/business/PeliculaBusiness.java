package cr.ac.ucr.paraiso.dsw4.renting.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cr.ac.ucr.paraiso.dsw4.renting.data.ActorData;
import cr.ac.ucr.paraiso.dsw4.renting.data.GeneroData;
import cr.ac.ucr.paraiso.dsw4.renting.data.PeliculaData;
import cr.ac.ucr.paraiso.dsw4.renting.domain.Actor;
import cr.ac.ucr.paraiso.dsw4.renting.domain.Genero;
import cr.ac.ucr.paraiso.dsw4.renting.domain.Pelicula;

@Service
public class PeliculaBusiness {
    private final PeliculaData peliculaData;
    private final ActorData actorData;
    private final GeneroData generoData;

    public PeliculaBusiness(PeliculaData peliculaData, ActorData actorData, GeneroData generoData) {
        this.peliculaData = peliculaData;
        this.actorData = actorData;
        this.generoData = generoData;
    }
    
    @Transactional(readOnly = true)
    public List<Pelicula> findMoviesByTitleOrGenre(String title, String genre) {
        return peliculaData.findMoviesByTitleOrGenre(
                title == null ? "" : title.trim(),
                genre == null ? "" : genre.trim());
    }

    @Transactional
    public Pelicula save(Pelicula pelicula) {
        if (pelicula.getGenero() == null) {
            throw new IllegalArgumentException("El género es obligatorio");
        }
        Genero genero = generoData.findById(pelicula.getGenero().getGeneroId())
                .orElseThrow(() -> new IllegalArgumentException("El género indicado no existe"));
        pelicula.setGenero(genero);

        List<Integer> actorIds = pelicula.getActores() == null
                ? List.of()
                : pelicula.getActores().stream()
                .map(Actor::getActorId)
                .distinct()
                .toList();
        Map<Integer, Actor> actoresExistentes = actorData.findAllById(actorIds).stream()
                .collect(Collectors.toMap(Actor::getActorId, Function.identity()));
        if (actoresExistentes.size() != actorIds.size()) {
            throw new IllegalArgumentException("Uno o más actores indicados no existen");
        }
        pelicula.setActores(actorIds.stream()
                .map(actoresExistentes::get)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll));

        Pelicula peliculaGuardada = peliculaData.save(pelicula);
        return peliculaData.findByPeliculaId(peliculaGuardada.getPeliculaId())
                .orElseThrow();
    }
}
