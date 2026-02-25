package com.gus.pet.entity;

import static com.gus.pet.entity.Pet.NOT_PROVIDED;

public enum petType {
    PET_TYPE_NOT_PROVIDED(NOT_PROVIDED),
    PET_TYPE_DOG("Dog"),
    PET_TYPE_CAT("Cat");

    public final String type;

    petType(String type) {
        this.type = type;
    }
}
