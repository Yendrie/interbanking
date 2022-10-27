package org.interbanking.repositories;

import org.interbanking.entities.Client;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static io.smallrye.openapi.api.util.MergeUtil.mergeObjects;

@ApplicationScoped
public class ClientRepository {

    @Inject
    EntityManager em;

    @Transactional
    public Client createdClient(Client client) {
        em.persist(client);
        return em.find(Client.class, client.getId());
    }

    @Transactional
    public Client updatedClient(Client client, Long id) {
        Client clientdb = em.find(Client.class, id);
        if (clientdb == null) {
            return null;
        }
        Client result = mergeObjects(clientdb, client);
        em.persist(result);
        return result;
    }

    @Transactional
    public Client deleteClient(Long id) {
        Client clientdb = em.find(Client.class, id);
        if (clientdb == null) {
            return null;
        }
        clientdb = em.merge(clientdb);
        em.remove(clientdb);
        return clientdb;
    }

    @Transactional
    public List<Client> listClient() {
        return em.createQuery("select p from Client p").getResultList();
    }

    @Transactional
    public Client findClientById(Long id) {
        return em.find(Client.class, id);
    }
}
