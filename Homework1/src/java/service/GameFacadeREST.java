package service;

import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import model.entities.ErrorMessage;
import model.entities.Game;

/**
 *
 * @author Ruben
 */
@Stateless
@Path("game")
public class GameFacadeREST extends AbstractFacade<Game> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public GameFacadeREST() {
        super(Game.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findAll(@QueryParam("gameType") String type,
            @QueryParam("console") String console) {
        try {
            String consulta;
            TypedQuery<Game> query;

            if (type != null && console != null) {
                // Ambos parámetros proporcionados
                consulta = "SELECT g FROM Game g WHERE g.gameType = :gameType AND g.console = :console ORDER BY g.name";
                query = em.createQuery(consulta, Game.class);
                query.setParameter("gameType", type);
                query.setParameter("console", console);
            } else if (type != null) {
                // Solo gameType proporcionado
                consulta = "SELECT g FROM Game g WHERE g.gameType = :gameType ORDER BY g.name";
                query = em.createQuery(consulta, Game.class);
                query.setParameter("gameType", type);
            } else if (console != null) {
                // Solo console proporcionado
                consulta = "SELECT g FROM Game g WHERE g.console = :console ORDER BY g.name";
                query = em.createQuery(consulta, Game.class);
                query.setParameter("console", console);
            } else {
                // Ningún parámetro proporcionado
                consulta = "SELECT g FROM Game g ORDER BY g.name";
                query = em.createQuery(consulta, Game.class);
            }

            List<Game> games = query.getResultList();
            return Response.ok(games).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error processing request").build();
        }
    }

    @POST
    @Secured
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createGame(Game entity) {
        // Verifica si ya existe un juego con el mismo nombre
        List<Game> existingGames = em.createNamedQuery("Game.findName", Game.class)
                .setParameter("name", entity.getName())
                .getResultList();

        if (!existingGames.isEmpty()) {
            // Si ya existe un juego con el mismo nombre, devuelve un error 409 Conflict
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ErrorMessage("Ya existe un juego con el mismo nombre"))
                    .build();
        }

        // Si no existe, crea el nuevo juego y devuelve 201 Created
        super.create(entity);
        return Response.status(Response.Status.CREATED).build();
    }

}
