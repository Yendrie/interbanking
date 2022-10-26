package org.interbanking;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class BackendClientsTest {

    @Test
    public void listClients() {
        given()
                .when().get("/clients")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));

        given()
                .when().get("/clients/1")
                .then()
                .statusCode(200)
                .body("id", is(0));
    }

    @Test
    public void createdClient() {
        given()
                .body("{\"name\": \"Geider\",\"lastname\": \"Arevalo\"," +
                        "\"email\": \"yr@gmail.com\",\"phone\": \"09858965\",\"address\"" +
                        ": \"Abreu 2010\",\"rut\": \"1596326\",\"enterpriseId\": \"8596\"}")
                .contentType(ContentType.JSON)
                .when()
                .post("/clients")
                .then()
                .statusCode(200);
    }

    @Test
    public void updatedClient() {
        given()
                .body("{\"id\": \"4\"name\": \"Juan\",\"lastname\": \"Perez\"," +
                        "\"email\": \"yr@gmail.com\",\"phone\": \"09858965\",\"address\"" +
                        ": \"Abreu 2010\",\"rut\": \"1596326\",\"enterpriseId\": \"8596\"}")
                .contentType(ContentType.JSON)
                .when()
                .put("/clients")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteClient() {
        given()
                .body("{\"id\": \"6\"}")
                .contentType(ContentType.JSON)
                .when()
                .delete("/clients")
                .then()
                .statusCode(204);
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void testCreatedClient() {
    }

    @Test
    void testUpdatedClient() {
    }

    @Test
    void testDeleteClient() {
    }

    @Test
    void testListClient() {
    }
}
