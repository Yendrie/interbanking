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

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BackendClients {

    @Inject
    ClientRepository clientRepository;

    @POST
    @RolesAllowed({"admin", "user"})
    public Response createdClient(Client client) {
        try {
            client = clientRepository.createdClient(client);
            return client == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(client).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        }
    }

    @PATCH
    @Path("/{id}")
    @RolesAllowed({"admin", "user"})
    public Response updatedClient(Client client, @PathParam("id") Long id) {
        try {
            client = clientRepository.updatedClient(client, id);
            return client == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(client).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"admin", "user"})
    public Response deleteClient(@PathParam("id") Long id) {
        try {
            Client client = clientRepository.deleteClientById(id);
            return client == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(client).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        }
    }

    @GET
    @PermitAll
    public Response listClient() {
        try {
            return Response.ok(clientRepository.listClient()).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        }
    }


    @GET
    @Path("/{id}")
    @PermitAll
    public Response findClientById(@PathParam("id") Long id) {
        try {
            Client client = clientRepository.findClientById(id);
            return client == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(client).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        }
    }
}