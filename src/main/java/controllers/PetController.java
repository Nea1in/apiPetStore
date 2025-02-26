package controllers;

import config.PetConfig;
import io.restassured.response.Response;
import models.pet.Pet;

public class PetController extends BaseController {

    private final PetConfig petConfig;

    public PetController() {
        petConfig = new PetConfig();
    }

    public Response createPet(Pet pet) {
        return post(petConfig.getAddPetEndpoint(), pet);
    }

    public Response updatePet(Long petId, Pet pet) {
        return put(petConfig.getUpdatePetEndpoint(), pet);
    }

    public Response getPetById(Long petId) {

        return getById(petConfig.getGetPetByIdEndpoint(petId));
    }

    public Response deletePet(Long petId) {
        return delete(petConfig.getDeletePetEndpoint(petId));
    }

}
