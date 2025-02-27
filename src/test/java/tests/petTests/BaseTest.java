package tests.petTests;

import controllers.PetController;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    protected PetController petController;

    private final List<BigInteger> createdPetIds = new ArrayList<>();

    @BeforeClass
    public void setup() {
        petController = new PetController();
    }

    protected void trackCreatedPet(BigInteger petId) {
        createdPetIds.add(petId);
    }

    @AfterMethod
    public void cleanup() {
        for (BigInteger petId : createdPetIds) {
            petController.deletePet(petId);
        }
        createdPetIds.clear();
    }
}
