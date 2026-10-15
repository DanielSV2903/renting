package cr.ac.ucr.paraiso.dsw4.renting.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pelicula")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pelicula_id")
    private int peliculaId;

    @Column(name = "titulo", length = 50, nullable = false)
    private String titulo;

    @Column(name = "subtitulada", nullable = false)
    private boolean subtitulada;

    @Column(name = "estreno", nullable = false)
    private boolean estreno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "genero_id", nullable = false)
    private Genero genero;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Pelicula_Actor",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actores;

    public Pelicula(){
        this.genero = new Genero();
        this.actores = new ArrayList<>();
    }
    

    public Pelicula(int peliculaId, String titulo, boolean subtitulada, boolean estreno, Genero genero) {
        this.peliculaId = peliculaId;
        this.titulo = titulo;
        this.subtitulada = subtitulada;
        this.estreno = estreno;
        this.genero = genero;
        this.actores = new ArrayList<>();
    }
    public Pelicula(int peliculaId, String titulo, boolean subtitulada, boolean estreno, Genero genero,List<Actor> actores) {
        this.peliculaId = peliculaId;
        this.titulo = titulo;
        this.subtitulada = subtitulada;
        this.estreno = estreno;
        this.genero = genero;
        this.actores = actores;
    }


    public int getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(int peliculaId) {
        this.peliculaId = peliculaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isSubtitulada() {
        return subtitulada;
    }

    public void setSubtitulada(boolean subtitulada) {
        this.subtitulada = subtitulada;
    }

    public boolean isEstreno() {
        return estreno;
    }

    public void setEstreno(boolean estreno) {
        this.estreno = estreno;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public List<Actor> getActores() {
        return actores;
    }

    public void setActores(List<Actor> actores) {
        this.actores = actores;
    }
    



}
