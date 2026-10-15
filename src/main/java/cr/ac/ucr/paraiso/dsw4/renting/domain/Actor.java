package cr.ac.ucr.paraiso.dsw4.renting.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Actor", schema = "dbo")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private int actorId;

    @Column(name = "nombre_actor", length = 50, nullable = false)
    private String nombreActor;

    @Column(name = "apellidos_actor", length = 50, nullable = false)
    private String apellidosActor;

    
    public Actor() {
    }
    
    public Actor(int actorId, String nombreActor, String apellidosActor) {
        this.actorId = actorId;
        this.nombreActor = nombreActor;
        this.apellidosActor = apellidosActor;
    }

    public int getActorId() {
        return actorId;
    }
    public void setActorId(int actorId) {
        this.actorId = actorId;
    }
    public String getNombreActor() {
        return nombreActor;
    }
    public void setNombreActor(String nombreActor) {
        this.nombreActor = nombreActor;
    }
    public String getApellidosActor() {
        return apellidosActor;
    }
    public void setApellidosActor(String apellidosActor) {
        this.apellidosActor = apellidosActor;
    }
    

}
