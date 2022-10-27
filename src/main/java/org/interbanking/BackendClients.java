package org.interbanking;

import org.interbanking.entities.Client;
import org.interbanking.repositories.ClientRepository;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BackendClients {

    @Inject
    ClientRepository clientRepository;

    @POST
    @RolesAllowed({"admin", "user"})
    public Response createdClient(Client client) {
        client = clientRepository.createdClient(client);
        if (client == null) {
            return Response.status(Status.NOT_FOUND).build();
        } else {
            return Response.ok(client).build();
        }
    }

    @PATCH
    @Path("/{id}")
    @RolesAllowed({"admin", "user"})
    public Response updatedClient(Client client, @PathParam("id") Long id) {
        try {
            client = clientRepository.updatedClient(client, id);
            if (client == null) {
                return Response.status(Status.NOT_FOUND).build();
            } else {
                return Response.ok(client).build();
            }
        } catch (Exception e) {
            throw new WebApplicationException(e.getMessage(), Status.BAD_REQUEST);
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"admin", "user"})
    public Response deleteClient(@PathParam("id") Long id) {
        try {
            Client client = clientRepository.deleteClient(id);
            if (client == null) {
                return Response.status(Status.NOT_FOUND).build();
            } else {
                return Response.ok(client).build();
            }
        } catch (Exception e) {
            throw new WebApplicationException(e.getMessage(), Status.BAD_REQUEST);
        }
    }

    @GET
    @PermitAll
    public List<Client> listClient() {
        return clientRepository.listClient();
    }


    @GET
    @Path("/{id}")
    @PermitAll
    public Response findClientById(@PathParam("id") Long id) {
        Client client = clientRepository.findClientById(id);
        if (client == null) {
            return Response.status(Status.NOT_FOUND).build();
        } else {
            return Response.ok(client).build();
        }
    }
}