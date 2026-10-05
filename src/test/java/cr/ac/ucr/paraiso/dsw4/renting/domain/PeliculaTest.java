package cr.ac.ucr.paraiso.dsw4.renting.domain;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

 @SpringBootTest
public class PeliculaTest {
    @Test
    public void whenPeliculaIsCreated_ThenPeliculaIsNotNull() {
        //Arrange
        Genero genero;
        Pelicula pelicula;
        //Act
        genero = new Genero(1, "Acción");
        pelicula = new Pelicula(1, "Avengers", true, true, genero);
        //Assert
        assertNotNull(pelicula);
        assert pelicula.getPeliculaId() == 1;
        assert pelicula.getTitulo().equals("Avengers");
        assert pelicula.isSubtitulada();
        assert pelicula.isEstreno();
        assert pelicula.getGenero().getNombreGenero().equals("Acción");
    }
    @Test
    public void whenPeliculaIsCreatedWithActors_ThenPeliculaIsNotNull() {
        //Arrange
        Genero genero;
        Pelicula pelicula;
        Actor actor1, actor2;
        //Act
        genero = new Genero(1, "Acción");
        actor1 = new Actor(1, "Robert", "Downey Jr.");
        actor2 = new Actor(2, "Chris", "Evans");
        pelicula = new Pelicula(1, "Avengers", true, true, genero);
        pelicula.getActores().add(actor1);
        pelicula.getActores().add(actor2);
        //Assert
        assertNotNull(pelicula);
        assert pelicula.getPeliculaId() == 1;
        assert pelicula.getTitulo().equals("Avengers");
        assert pelicula.isSubtitulada();
        assert pelicula.isEstreno();
        assert pelicula.getGenero().getNombreGenero().equals("Acción");
        assert pelicula.getActores().size() == 2;
        assert pelicula.getActores().get(0).getNombreActor().equals("Robert");
    }
    @Test
    public void whenPeliculaIsCreated_ThenGeneroIsNotNull() {
        //Arrange
        Genero genero;
        Pelicula pelicula;
        //Act
        genero = new Genero(1, "Acción");
        pelicula = new Pelicula(1, "Avengers", true, true, genero);
        //Assert
        assertNotNull(pelicula.getGenero());
    }
    @Test
    public void whenPeliculaIsCreated_ThenActoresIsNotNull() {
        //Arrange
        Genero genero;
        Pelicula pelicula;
        //Act
        genero = new Genero(1, "Acción");
        pelicula = new Pelicula(1, "Avengers", true, true, genero);
        //Assert
        assertNotNull(pelicula.getActores());
    }
    @Test
    public void whenPeliculaIsCreatedWithAllParameters_ThenNothingIsNotNull() {
        //Arrange
        Genero genero;
        Pelicula pelicula;
        Actor actor1, actor2;
        ArrayList<Actor> actores = new ArrayList<>();
        //Act
        genero = new Genero(1, "Acción");
        actor1 = new Actor(1, "Robert", "Downey Jr.");
        actor2 = new Actor(2, "Chris", "Evans");
        actores.add(actor1);
        actores.add(actor2);
        pelicula = new Pelicula(1, "Avengers", true, true, genero, actores);
        //Assert
        assertNotNull(pelicula);
        assert pelicula.getPeliculaId() == 1;
        assert pelicula.getTitulo().equals("Avengers");
        assert pelicula.isSubtitulada();
        assert pelicula.isEstreno();
        assert pelicula.getGenero().getNombreGenero().equals("Acción");
        assert pelicula.getActores().size() == 2;
    }
       
}
