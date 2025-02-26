import controllers.PetController;
import io.restassured.response.Response;
import models.pet.Category;
import models.pet.Pet;
import models.pet.Tag;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;


public class PetControllerTest {

    private final PetController petController = new PetController();



    @Test
    public void testCreatePetAndTakePetById() {
        Category category = new Category(1, "Dog");

        Tag tag1 = new Tag(1, "Friendly");
        Tag tag2 = new Tag(2, "Active");

        String photoUrl = "http://example.com/photo.jpg";

        Pet pet = new Pet();
        pet.setName("Buddy");
        pet.setCategory(category);
        pet.setPhotoUrls(Arrays.asList(photoUrl));
        pet.setTags(Arrays.asList(tag1, tag2));
        pet.setStatus("available");

        Response response = petController.createPet(pet);

        Pet createdPet = response.as(Pet.class);
        Long petId = createdPet.getId();

        Response response1 = petController.getPetById(petId);
        Pet pet1 = response1.as(Pet.class);
        System.out.println(pet1.toString());

        assertEquals(pet1.getName(), "Buddy");
        pet1.setName("Ban");
        response = petController.updatePet(petId,pet1);
        pet1 = response.as(Pet.class);
        System.out.println(pet1.toString());
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }


}
