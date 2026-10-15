package cr.ac.ucr.paraiso.dsw4.renting.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Genero")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genero_id")
    private int generoId;

    @Column(name = "nombre_genero", length = 50, nullable = false)
    private String nombreGenero;

    public Genero(){

    }
    
    public Genero(int generoId, String nombreGenero) {
        this.generoId = generoId;
        this.nombreGenero = nombreGenero;
    }

    public int getGeneroId() {
        return generoId;
    }
    public void setGeneroId(int generoId) {
        this.generoId = generoId;
    }
    public String getNombreGenero() {
        return nombreGenero;
    }
    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    

}
