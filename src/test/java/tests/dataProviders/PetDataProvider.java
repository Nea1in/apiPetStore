package tests.dataProviders;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.pet.Pet;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PetDataProvider {

    @DataProvider(name = "petData")
    public static Object[][] petDataProvider() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Pet> pets = objectMapper.readValue(new File("src/test/resources/petData.json"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Pet.class));

            return pets.stream().map(pet -> new Object[]{pet}).toArray(Object[][]::new);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load pet data from JSON file", e);
        }
    }
}