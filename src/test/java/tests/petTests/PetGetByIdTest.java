package tests.petTests;

import config.JsonSchemaPaths;
import data.factory.PetData;
import io.restassured.response.Response;
import models.pet.Pet;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import utils.JsonSchemaUtils;

import java.math.BigInteger;

import static org.testng.Assert.*;

public class PetGetByIdTest extends BaseTestForPet {

   @Test(description = "Test to create a new pet and then retrieve it using its ID")
    public void testCreateAndGetPet() {
        Pet pet = PetData.generatePet();
        logger.info("Creating pet: {}", pet.getName());
        Response response = petController.createPet(pet);
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Pet not created");

        Pet createdPet = response.as(Pet.class);
        BigInteger petId = createdPet.getId();
        trackCreatedPet(petId);

        assertNotNull(petId, "Pet ID is null after creation!");
        logger.info("Pet created successfully with ID: {}", petId);

        Response responseGet = petController.getPetById(petId);
        assertEquals(responseGet.getStatusCode(), HttpStatus.SC_OK);
        assertTrue(JsonSchemaUtils.isValidJsonSchema(responseGet, JsonSchemaPaths.PET_SCHEMA),
                "JSON-schema validation failed");
       Pet getPet = responseGet.as(Pet.class);
       assertEquals(getPet.getName(), pet.getName());
    }
}
