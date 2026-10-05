package cr.ac.ucr.paraiso.dsw4.renting.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cr.ac.ucr.paraiso.dsw4.renting.data.PeliculaData.PeliculaExtractor;
import cr.ac.ucr.paraiso.dsw4.renting.domain.Actor;
import cr.ac.ucr.paraiso.dsw4.renting.domain.Genero;
import cr.ac.ucr.paraiso.dsw4.renting.domain.Pelicula;

@Repository
public class PeliculaData {
    @Autowired
        private JdbcTemplate jdbcTemplate;
    @Autowired 
    private DataSource dataSource;
        public PeliculaData() {
        }


   public List<Pelicula> findMoviesByTitleOrGenre(String title, String genre) {
     String sqlSelect = """
                SELECT
                    p.pelicula_id,
                    p.titulo,
                    p.genero_id,
                    g.nombre_genero,
                    p.subtitulada,
                    p.estreno,
                    pa.actor_id,
                    a.nombre_actor,
                    a.apellidos_actor
                FROM Pelicula p
                INNER JOIN Genero g
                    ON p.genero_id = g.genero_id
                LEFT JOIN Pelicula_Actor pa
                    ON p.pelicula_id = pa.pelicula_id
                LEFT JOIN Actor a
                    ON pa.actor_id = a.actor_id
                WHERE LOWER(p.titulo) LIKE ?
                or LOWER(g.nombre_genero) LIKE ?
                """;
             title = title.toLowerCase();
             genre = genre.toLowerCase();
            String titleLike = (title == null || title=="" ? "" : "%" + title.trim() + "%");    
            String genreLike = (genre == null || genre=="" ? "" : "%" + genre.trim() + "%");
            // Pasamos el SQL, la instancia del extractor y los parámetros

            return jdbcTemplate.query(
                    sqlSelect,new PeliculaExtractor(),titleLike, genreLike);            
    }

    public Pelicula save(Pelicula pelicula) throws SQLException{
        Connection connection=null;
        try{
            connection=dataSource.getConnection();
            connection.setAutoCommit(false);
            SimpleJdbcCall simpleJdbcCallPelicula = new SimpleJdbcCall(jdbcTemplate).
                    withCatalogName("dbo").
                    withProcedureName("Pelicula_Insert").withoutProcedureColumnMetaDataAccess().
                    declareParameters(new SqlOutParameter("@pelicula_id", Types.INTEGER)).
                    declareParameters(new SqlParameter("@titulo", Types.VARCHAR)).
                    declareParameters(new SqlParameter("@subtitulada", Types.BIT)).
                    declareParameters(new SqlParameter("@estreno", Types.BIT)).
                    declareParameters(new SqlParameter("@genero_id", Types.INTEGER));
            Map<String, Object> outParameters = simpleJdbcCallPelicula.execute(pelicula.getTitulo(), pelicula.isSubtitulada(), pelicula.isEstreno(), pelicula.getGenero().getGeneroId());
            pelicula.setPeliculaId(Integer.parseInt(outParameters.get("@pelicula_id").toString()));
           
            SimpleJdbcCall simpleJdbcCallPeliculaActor = new SimpleJdbcCall(jdbcTemplate).
                    withCatalogName("dbo").
                    withProcedureName("PeliculaActor_Insert").withoutProcedureColumnMetaDataAccess().
                    declareParameters(new SqlParameter("@pelicula_id", Types.INTEGER)).
                    declareParameters(new SqlParameter("@actor_id", Types.INTEGER));
            for(Actor actor:pelicula.getActores())
                simpleJdbcCallPeliculaActor.execute(pelicula.getPeliculaId(), actor.getActorId());
            connection.commit();
        
        }catch(SQLException e){
            throw e;
        }finally{
            if (connection!= null) {
                
            }
        }
        return pelicula;            
    }


        // Implementación del extractor de resultados
    class PeliculaExtractor implements ResultSetExtractor<List<Pelicula>> {
 
        @Override
        public List<Pelicula> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Integer, Pelicula> peliculas = new HashMap<>();
            Pelicula pelicula = null;
            while (rs.next()) {//Pregunta al resultset si tiene registros por leer
                int peliculaId = rs.getInt("pelicula_id");
                pelicula = peliculas.get(peliculaId);
                if (pelicula == null) {        
                    pelicula = new Pelicula();
                    pelicula.setPeliculaId(peliculaId);
                    pelicula.setTitulo(rs.getString("titulo"));
                    pelicula.setSubtitulada(rs.getBoolean("subtitulada"));
                    pelicula.setEstreno(rs.getBoolean("estreno"));
                    Genero genero = new Genero();
                    genero.setGeneroId(rs.getInt("genero_id"));
                    genero.setNombreGenero(rs.getString("nombre_genero"));
                    pelicula.setGenero(genero);
                    peliculas.put(peliculaId, pelicula);
                }
                int actorId = rs.getInt("actor_id");
                if (actorId > 0) {//verifica si el actor existe, si es mayor a 0 entonces existe
                    Actor actor = new Actor();
                    actor.setActorId(actorId);
                    actor.setNombreActor(rs.getString("nombre_actor"));
                    actor.setApellidosActor(rs.getString("apellidos_actor"));       
                    pelicula.getActores().add(actor);         
                    // Agregar actor a la lista de actores de la película
                }
            }//While
            return List.copyOf(peliculas.values());
        }
    }
}


