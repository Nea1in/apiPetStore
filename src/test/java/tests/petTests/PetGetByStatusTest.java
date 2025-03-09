package tests.petTests;

import io.restassured.response.Response;
import models.pet.Pet;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import tests.dataProviders.PetDataProvider;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class PetGetByStatusTest extends BaseTestForPet {

    @Test(dataProvider = "petData", dataProviderClass = PetDataProvider.class, description = "Test to create a new pet and then retrieve it using its status")
    public void testCreateAndGetPetsByStatus(Pet pet) {
        logger.info("Creating pet: {}", pet.getName());

        Response response = petController.createPet(pet);
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Pet not created");

        Pet createdPet = response.as(Pet.class);
        BigInteger petId = createdPet.getId();
        trackCreatedPet(petId);

        assertNotNull(petId, "Pet ID is null after creation!");
        logger.info("Pet created successfully with ID: {}", petId);

        Response responseGet = petController.getPetsByStatus(pet.getStatus());
        assertEquals(responseGet.getStatusCode(), HttpStatus.SC_OK);

        List<Pet> retrievedPets = Arrays.asList(responseGet.as(Pet[].class));
        boolean petExists = retrievedPets.stream()
                .anyMatch(p -> p.getName().equals(pet.getName()));

        assertTrue(petExists, "Created pet not found in the list of pets by status!");
    }
}

