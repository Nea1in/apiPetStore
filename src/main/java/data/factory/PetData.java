package data.factory;

import com.github.javafaker.Faker;
import models.pet.Category;
import models.pet.Pet;
import models.pet.Tag;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PetData {
    private static final Faker faker = new Faker();

    public static Pet generatePet() {
        Pet pet = new Pet();
        pet.setId(BigInteger.valueOf(faker.number().randomNumber()));
        pet.setCategory(generateCategory());
        pet.setName(faker.funnyName().name());
        pet.setPhotoUrls(generatePhotoUrls());
        pet.setTags(generateTags());
        pet.setStatus("available");
        return pet;
    }

    private static Category generateCategory() {
        Category category = new Category();
        category.setId(faker.number().randomDigitNotZero());
        category.setName(faker.animal().name());
        return category;
    }

    private static List<String> generatePhotoUrls() {
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(faker.internet().image());
        return photoUrls;
    }

    private static List<Tag> generateTags() {
        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Tag tag = new Tag();
            tag.setId(faker.number().randomDigitNotZero());
            tag.setName(faker.book().title());
            tags.add(tag);
        }
        return tags;
    }
}
