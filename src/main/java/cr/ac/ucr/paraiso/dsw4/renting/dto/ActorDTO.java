package cr.ac.ucr.paraiso.dsw4.renting.dto;

import jakarta.validation.constraints.NotNull;

public class ActorDTO {
       @NotNull(message = "Actor ID is mandatory")
       private int actorId;
       public int getActorId() {
           return actorId;
       }
       public void setActorId(int actorId) {
           this.actorId = actorId;
       }
   }    
