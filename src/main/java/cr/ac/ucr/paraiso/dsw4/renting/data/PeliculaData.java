package cr.ac.ucr.paraiso.dsw4.renting.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cr.ac.ucr.paraiso.dsw4.renting.domain.Pelicula;

public interface PeliculaData extends JpaRepository<Pelicula, Integer> {

    @EntityGraph(attributePaths = {"genero", "actores"})
    Optional<Pelicula> findByPeliculaId(int peliculaId);

    @EntityGraph(attributePaths = {"genero", "actores"})
    @Query("""
            SELECT DISTINCT pelicula
            FROM Pelicula pelicula
            JOIN pelicula.genero genero
            WHERE (:title = '' AND :genre = '')
               OR (:title <> '' AND LOWER(pelicula.titulo) LIKE LOWER(CONCAT('%', :title, '%')))
               OR (:genre <> '' AND LOWER(genero.nombreGenero) LIKE LOWER(CONCAT('%', :genre, '%')))
            """)
    List<Pelicula> findMoviesByTitleOrGenre(
            @Param("title") String title,
            @Param("genre") String genre);
}

