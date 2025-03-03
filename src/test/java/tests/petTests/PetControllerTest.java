package tests.petTests;


import io.restassured.response.Response;
import models.pet.Category;
import models.pet.Pet;
import models.pet.Tag;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class PetControllerTest extends BaseTestForPet {

    @DataProvider(name = "petData")
    public Object[][] petDataProvider() {
        Pet pet1 = new Pet();
        pet1.setName("Buddy");
        pet1.setCategory(new Category(1, "Dog"));
        pet1.setPhotoUrls(Arrays.asList("http://example.com/photo.jpg"));
        pet1.setTags(Arrays.asList(new Tag(1, "Friendly"), new Tag(2, "Active")));
        pet1.setStatus("available");

        Pet pet2 = new Pet();
        pet2.setName("Milo");
        pet2.setCategory(new Category(2, "Cat"));
        pet2.setPhotoUrls(Arrays.asList("http://example.com/photo.jpg"));
        pet2.setTags(Arrays.asList(new Tag(3, "Playful")));
        pet2.setStatus("available");

        return new Object[][]{
                {pet1},{pet2}
        };
    }

    @Test(dataProvider = "petData", description = "Test to create a new pet and then retrieve it using its ID")
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

    @Test(dataProvider = "petData", description = "Test to create and then update a pet's details")
    public void testCreateAndUpdatePet(Pet pet) {
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