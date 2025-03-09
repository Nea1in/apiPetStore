package controllers;

import config.PetConfig;
import io.restassured.response.Response;
import models.pet.Pet;

import java.math.BigInteger;

public class PetController extends BaseController {

    private final PetConfig petConfig;

    public PetController() {
        petConfig = new PetConfig();
    }

    public Response createPet(Pet pet) {
        return post(petConfig.getAddPetEndpoint(), pet);
    }

    public Response updatePet(Pet pet) {
        return put(petConfig.getUpdatePetEndpoint(),pet);
    }

    public Response getPetById(BigInteger petId) {
        return getById(petConfig.getGetPetByIdEndpoint(petId));
    }

    public Response getPetsByStatus(String status) {
        return getByStatus(petConfig.getPetsByStatusEndpoint(status));
    }

    public Response deletePet(BigInteger petId) {
        return delete(petConfig.getDeletePetEndpoint(petId));
    }

}
