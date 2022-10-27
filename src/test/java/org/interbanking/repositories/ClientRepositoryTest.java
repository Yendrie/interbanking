package org.interbanking.repositories;

import io.quarkus.test.junit.QuarkusTest;
import org.interbanking.entities.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
class ClientRepositoryTest {

    @Inject
    ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createdClient() {
        Client client = new Client();
        client.setName("Test");
        client.setAddress("Test");
        client.setEmail("Test");
        client.setEnterpriseId("Test");
        client.setLastname("Test");
        client.setPhone("Test");
        client.setRut("Test");
        client = clientRepository.createdClient(client);
        Assertions.assertNotNull(client.getId());
    }

    @Test
    void updatedClient() {
        Client client = new Client();
        client.setName("Test2");
        client.setAddress("Test2");
        client.setEmail("Test2");
        client.setEnterpriseId("Test2");
        client.setLastname("Test2");
        client.setPhone("Test2");
        client.setRut("Test2");

        Client lastClient = clientRepository.listClient().get(clientRepository.listClient().size() - 1);

        client = clientRepository.updatedClient(client, lastClient.getId());
        Assertions.assertNotNull(client);

        client = clientRepository.updatedClient(client, lastClient.getId() + 1);
        Assertions.assertNull(client);
    }

    @Test
    void deleteClient() {
        Client deleted = clientRepository.deleteClient(clientRepository.listClient().get(clientRepository.listClient().size() - 1).getId());
        Assertions.assertNotNull(deleted);

        deleted = clientRepository.findClientById(deleted.getId());
        Assertions.assertNull(deleted);

        deleted = clientRepository.deleteClient(clientRepository.listClient().get(clientRepository.listClient().size() - 1).getId() + 1);
        Assertions.assertNull(deleted);
    }

    @Test
    void listClient() {
        Assertions.assertNotNull(clientRepository.listClient());
    }
}