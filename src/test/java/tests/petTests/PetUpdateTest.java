package tests.petTests;

import data.factory.PetData;
import io.restassured.response.Response;
import models.pet.Pet;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import tests.dataProviders.PetDataProvider;

import java.math.BigInteger;

import static org.testng.Assert.assertEquals;

public class PetUpdateTest extends BaseTestForPet {

    @Test(description = "Test to create and then update a pet's details")
    public void testCreateAndUpdatePet() {
        Pet pet = PetData.generatePet();
        Response response = petController.createPet(pet);
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

        Pet createdPet = response.as(Pet.class);
        BigInteger petId = createdPet.getId();
        trackCreatedPet(petId);

        logger.info("Updating pet ID {} to new name: UpdatedName", petId);
        createdPet.setName("UpdatedName");
        Response updateResponse = petController.updatePet(createdPet);
        assertEquals(updateResponse.getStatusCode(), HttpStatus.SC_OK);

        Pet updatedPet = updateResponse.as(Pet.class);
        assertEquals(updatedPet.getName(), "UpdatedName");
        logger.info("Pet updated successfully: {}", updatedPet);
    }
}

