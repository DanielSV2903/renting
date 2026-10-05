package cr.ac.ucr.paraiso.dsw4.renting.dto;

import jakarta.validation.constraints.NotNull;

public class GeneroDTO {
@NotNull(message = "Genre ID is mandatory")
       private int generoId;
       public int getGeneroId() {
           return generoId;
       }
       public void setGeneroId(int generoId) {
           this.generoId = generoId;
       }
   }
   
