package com.gus.pet.entity;

public class Pet {
    public static final String NOT_PROVIDED = "NOT PROVIDED";
    public String name;
    public String address;
    public String breed;
    public String age;
    public String weight;
    public petType type;
    public petSex sex;

    public Pet() {
        this.name = NOT_PROVIDED;
        this.breed = NOT_PROVIDED;
        this.age = NOT_PROVIDED;
        this.address = NOT_PROVIDED;
        this.type = petType.PET_TYPE_NOT_PROVIDED;
        this.sex = petSex.PET_SEX_NOT_PROVIDED;
        this.weight = NOT_PROVIDED;
    }

    public void printPet() {
        System.out.print(this.name + " - ");
        System.out.print(this.sex + " - ");
        System.out.print(this.address + " - ");
        System.out.print(this.age + "years old - ");
        System.out.print(this.weight + "kg - ");
        System.out.print(this.breed + "\n");
    }
}
