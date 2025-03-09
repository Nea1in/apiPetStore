package tests.petTests;

import controllers.PetController;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import tests.BaseTest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class BaseTestForPet extends BaseTest {

    protected PetController petController;
    private final List<BigInteger> createdPetIds = new ArrayList<>();

    @BeforeClass
    public void setup() {
        petController = new PetController();
        logger.info("PetController initialized");
    }

    protected void trackCreatedPet(BigInteger petId) {
        createdPetIds.add(petId);
        logger.info("Tracked created pet with ID: {}", petId);
    }

    @AfterMethod
    public void cleanup() {
        for (BigInteger petId : createdPetIds) {
            petController.deletePet(petId);
            logger.info("Deleted pet with ID: {}", petId);
        }
        createdPetIds.clear();
    }
}