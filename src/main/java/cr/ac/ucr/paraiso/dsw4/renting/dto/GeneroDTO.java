package cr.ac.ucr.paraiso.dsw4.renting.dto;

public class GeneroDTO {
   private int id;
   private String nombre;
   // Constructors
   public GeneroDTO() {}
   public GeneroDTO(int id, String nombre) {
       this.id = id;
       this.nombre = nombre;
   }
   public int getId() {
    return id;
   }
   public void setId(int id) {
    this.id = id;
   }
   public String getNombre() {
    return nombre;
   }
   public void setNombre(String nombre) {
    this.nombre = nombre;
   }
    
}
