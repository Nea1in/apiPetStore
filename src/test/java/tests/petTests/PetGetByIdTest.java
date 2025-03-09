package tests.petTests;

import io.restassured.response.Response;
import models.pet.Pet;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import tests.dataProviders.PetDataProvider;

import java.math.BigInteger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class PetGetByIdTest extends BaseTestForPet {

    @Test(dataProvider = "petData", dataProviderClass = PetDataProvider.class, description = "Test to create a new pet and then retrieve it using its ID")
    public void testCreateAndGetPet(Pet pet) {
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

        Pet retrievedPet = responseGet.as(Pet.class);
        assertEquals(retrievedPet.getName(), pet.getName());
    }
}
