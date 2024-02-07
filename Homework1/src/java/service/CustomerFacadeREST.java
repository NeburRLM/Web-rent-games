package service;

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import authn.Secured;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import model.entities.Customer;
import model.entities.ErrorMessage;

/**
 *
 * @author Ruben
 */
@Stateless
@Path("customer")
public class CustomerFacadeREST extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public CustomerFacadeREST() {
        super(Customer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Customer entity) {
        super.create(entity);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAllWithoutPasswd() {
        List<Customer> resultList = super.findAll();
        if (resultList.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("No existeixen usuaris")).build();
        }
        return Response.ok().entity(new GenericEntity<List<Customer>>(resultList) {
        }).build();
    }
    
    @GET
    @Secured
    @Path("/findByName")   
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findName(@QueryParam("name") String name) {
        String consulta;
        TypedQuery<Customer> query;
        
        consulta = "SELECT c FROM Customer c WHERE c.name = :name";
        query = em.createQuery(consulta, Customer.class);
        query.setParameter("name", name);
        Customer c = query.getSingleResult();
        if (c==null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("No existeix l'usuari amb id " + name)).build();
        }
        return Response.ok().entity(c).build();
    }
    

    @GET
    @Secured
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response find(@PathParam("id") Integer id) {
        Customer customer = super.find(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("No existeix l'usuari amb id " + id)).build();
        }
        return Response.ok().entity(customer).build();
    }

    @PUT
    @Secured
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response edit(@PathParam("id") Integer id, Customer entity) {

        Customer customer = super.find(id);

        if (customer == null) {
            return Response.status(Response.Status.FORBIDDEN).entity(new ErrorMessage("No puedes editar a este uusario")).build();
        }

        entity.setId(id);
        super.edit(entity);
        return Response.ok("Cambios realizados correctamente").build();
    }

}
