package org.bolivianjug.crud.boundary;

import org.bolivianjug.crud.control.NumbersApi;
import org.bolivianjug.crud.control.PersonService;
import org.bolivianjug.crud.entity.Person;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by julio.rocha on 27/6/20.
 *
 * @author julio.rocha
 */
@Path("people")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PeopleResource {

    @Inject
    @RestClient
    NumbersApi numbersApi;

    @Inject
    PersonService personService;


    @GET
    public List<Person> getAll() {
        return personService.listAll();
    }

    @GET
    @Path("{id}")
    public Person get(@PathParam("id") String id) {
        return personService.findById(id);
    }

    @POST
    @Transactional
    public Response save(Person person, @Context UriInfo uriInfo) {
        person.id = UUID.randomUUID().toString();
        person.numberMessage = numbersApi.getNumberMessage(person.number, "trivia");
        personService.persist(person);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder()
                .path(person.id);
        return Response.created(uriBuilder.build()).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") String id, Person newData) {
        newData.numberMessage = numbersApi.getNumberMessage(newData.number, "math");
        boolean updated = personService.customUpdate(id, newData);
        if (updated) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") String id) {
        boolean deleted = personService.deleteById(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
