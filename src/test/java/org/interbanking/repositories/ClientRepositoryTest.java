package org.interbanking.repositories;

import io.quarkus.test.junit.QuarkusTest;
import org.interbanking.entities.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.function.Function;


@QuarkusTest
class ClientRepositoryTest {

    @Inject
    ClientRepository clientRepository;

    private Function<Integer, Client> clientObject = (id) -> new Client("name" + id, "lastname" + id, "email" + id,
            "phone" + id, "address" + id, "rut" + id, "enterpriseId" + id);

    @Test
    void createdClientTest() {
        Client client = clientObject.apply(1);
        client = clientRepository.createdClient(client);
        Assertions.assertNotNull(client.getId());
    }

    @Test
    void updatedClientTest() {
        Client client = clientObject.apply(2);
        client = clientRepository.createdClient(client);

        if (clientRepository.listClient().size() > 0) {
            Client lastClient = clientRepository.listClient().get(clientRepository.listClient().size() - 1);

            client = clientRepository.updatedClient(client, lastClient.getId());
            Assertions.assertNotNull(client, "Client not updated");

            client = clientRepository.updatedClient(client, lastClient.getId() + 1);
            Assertions.assertNull(client, "Client not exist");
        }
    }

    @Test
    void deleteClientTest() {
        if (clientRepository.listClient().size() > 0) {
            Client deleted = clientRepository.deleteClientById(clientRepository.listClient().get(clientRepository.listClient().size() - 1).getId());
            Assertions.assertNotNull(deleted);

            deleted = clientRepository.findClientById(deleted.getId());
            Assertions.assertNull(deleted);

            if (clientRepository.listClient().size() > 0) {
                deleted = clientRepository.deleteClientById(clientRepository.listClient().get(clientRepository.listClient().size() - 1).getId() + 1);
                Assertions.assertNull(deleted);
            }
        } else {
            Assertions.assertTrue(true);
        }
    }

    @Test
    void listClient() {
        Assertions.assertNotNull(clientRepository.listClient());
    }
}