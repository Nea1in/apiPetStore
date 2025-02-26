package config;

public class PetConfig extends BaseConfig {


    public String getAddPetEndpoint() {
        return BASE_URL + getProperty("pet.add");
    }

    public String getUpdatePetEndpoint() {
        return BASE_URL + getProperty("pet.update");
    }

    public String getGetPetByIdEndpoint(Long petId) {
        return getProperty("pet.getById").replace("{petId}", String.valueOf(petId));
    }

    public String getDeletePetEndpoint(Long petId) {
        return getProperty("pet.delete").replace("{petId}", String.valueOf(petId));
    }
}
