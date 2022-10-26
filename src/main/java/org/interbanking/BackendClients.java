package org.interbanking;

import org.interbanking.entities.Client;
import org.interbanking.repositories.ClientRepository;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BackendClients {

    @Inject
    ClientRepository clientRepository;

    @POST
    @RolesAllowed({"admin", "user"})
    public Client createdClient(Client client) {
        return clientRepository.createdClient(client);
    }

    @PATCH
    @Path("/{id}")
    @RolesAllowed({"admin", "user"})
    public Client updatedClient(Client client, @PathParam("id") Long id) {
        try {
            return clientRepository.updatedClient(client, id);
        } catch (Exception e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.NOT_FOUND);
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"admin", "user"})
    public Client deleteClient(@PathParam("id") Long id) {
        try {
            return clientRepository.deleteClient(id);
        } catch (Exception e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.NOT_FOUND);
        }
    }

    @GET
    @PermitAll
    public List<Client> listClient() {
        return clientRepository.listClient();
    }
}