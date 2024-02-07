package service;

import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import model.entities.Customer;
import model.entities.ErrorMessage;
import model.entities.Game;
import model.entities.Rental;
import model.entities.RentalResponse;
import jakarta.ws.rs.*;
import model.entities.ListStringId;

@Stateless
@Path("rental")
public class RentalFacadeREST extends AbstractFacade<Rental> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public RentalFacadeREST() {
        super(Rental.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @POST
    @Secured
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response create(ListStringId id_games, @QueryParam("id_customer") Integer id_customer) {

        try {
            Customer customer = (Customer) em.createNamedQuery("Customer.find")
                    .setParameter("id", id_customer)
                    .getSingleResult();
            Collection<Game> g = new ArrayList<>();
            Game ga;

            Collection<Integer> id_game = id_games.getList();
            for (Integer stringGame : id_game) {
                ga = (Game) em.createNamedQuery("Game.find")
                        .setParameter("id", stringGame)
                        .getSingleResult();
                g.add(ga);
                if (!ga.isAvailability()) {
                    // El juego no está disponible
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity(new ErrorMessage("El joc amb id " + stringGame + " no està disponible ja que està en ús."))
                            .build();
                }
            }

            // Obtener la fecha actual
            Date currentDate = new Date();

            // Calcular la fecha después de sumar 14 días
            int amountDays = 7;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DAY_OF_MONTH, amountDays);
            Date returnDate = calendar.getTime();
            float setTotalAmount = 0;
            for (Game game : g) {
                setTotalAmount = game.getWeeklyRentalPrice() + setTotalAmount;
                game.check();

            }
            Rental entity = new Rental(returnDate, setTotalAmount, customer, g);

            super.create(entity);
            customer.addRental(entity);
            RentalResponse response = new RentalResponse(entity.getId(), entity.getTotalAmount(), entity.getRentalDate());
            response.setId(entity.getId());
            response.setTotalAmount(entity.getTotalAmount());
            response.setRentalDate(entity.getRentalDate());
            for (Game gg : g) {
                gg.putGameList(entity);
            }
            return Response.status(Response.Status.CREATED).entity(response).build();

        } catch (NoResultException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorMessage("No existeix el game o customer amb id " + id_customer))
                    .build();
        }
    }

    @GET
    @Secured
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response find(@PathParam("id") Integer id) {
        Rental rental = super.find(id);
        if (rental == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("No existeix el alquiler amb id " + id)).build();
        }
        return Response.ok().entity(rental).build();
    }

    @GET
    @Secured
    public Response login() {
        return Response.ok().build();
    }
}
