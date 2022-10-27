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
        return clientdb == null ? null : createdClient(mergeObjects(clientdb, client));
    }

    @Transactional
    public Client deleteClientById(Long id) {
        Client clientdb = em.find(Client.class, id);
        return clientdb == null ? null : deleteClient(clientdb);
    }

    @Transactional
    public Client deleteClient(Client clientdb) {
        em.remove(em.merge(clientdb));
        return clientdb;
    }

    @Transactional
    public List<Client> listClient() {
        return em.createQuery("select p from Client p", Client.class).getResultList();
    }

    @Transactional
    public Client findClientById(Long id) {
        return em.find(Client.class, id);
    }
}
