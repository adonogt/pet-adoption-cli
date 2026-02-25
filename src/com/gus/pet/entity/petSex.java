package com.gus.pet.entity;

import static com.gus.pet.entity.Pet.NOT_PROVIDED;

public enum petSex {
    PET_SEX_NOT_PROVIDED(NOT_PROVIDED),
    PET_SEX_MALE("Male"),
    PET_SEX_FEMALE("Female");

    public final String sex;
    petSex(String sex) {
        this.sex = sex;
    }
}
