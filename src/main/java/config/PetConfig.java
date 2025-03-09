package config;

import java.math.BigInteger;

public class PetConfig extends BaseConfig {


    public String getAddPetEndpoint() {
        return BASE_URL + getProperty("pet.add");
    }

    public String getUpdatePetEndpoint() {
        return BASE_URL + getProperty("pet.update");
    }

    public String getGetPetByIdEndpoint(BigInteger petId) {
        return getProperty("pet.getById").replace("{petId}", String.valueOf(petId));
    }

    public String getPetsByStatusEndpoint(String status) {
        return BaseConfig.getProperty("pet.getByStatus") + "?status=" + status;
    }

    public String getDeletePetEndpoint(BigInteger petId) {
        return getProperty("pet.delete").replace("{petId}", String.valueOf(petId));
    }
}
