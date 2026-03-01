package com.gus.pet.service;

import com.gus.pet.entity.Pet;
import com.gus.pet.entity.petSex;
import com.gus.pet.entity.petType;
import com.gus.pet.repository.PetRepository;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.gus.pet.entity.Pet.NOT_PROVIDED;


public class PetService {
    private Scanner input;

    public PetService(Scanner keyboard) {
        this.input = keyboard;
    }

    PetRepository repository = new PetRepository();
    Pet pet = new Pet();


    public void registerPet() {
        short counter = 1;
        String answer;
        String regex;
        Pattern patter;
        Matcher matcher;

        while (counter < 8) {
            switch (counter) {
                case 1:
                    repository.readForm(counter);
                    answer = input.nextLine();
                    regex = "([a-zA-Z])+(\\s)+([a-zA-Z])+";
                    patter = Pattern.compile(regex);
                    matcher = patter.matcher(answer);
                    if (matcher.find()) {
                        pet.name = matcher.group();
                    } else {
                        throw new IllegalArgumentException("You must write a name and a surname!");
                    }
                    counter++;
                    break;
                case 2:
                    repository.readForm(counter);
                    answer = input.nextLine();
                    if (answer.equals("Dog")) {
                        pet.type = petType.PET_TYPE_DOG;
                    } else if (answer.equals("Cat")) {
                        pet.type = petType.PET_TYPE_CAT;
                    } else {
                        pet.type = petType.PET_TYPE_NOT_PROVIDED;
                    }
                    counter++;
                    break;
                case 3:
                    repository.readForm(counter);
                    answer = input.nextLine();
                    if (answer.equals("Male")) {
                        pet.sex = petSex.PET_SEX_MALE;
                    } else if (answer.equals("Female")) {
                        pet.sex = petSex.PET_SEX_FEMALE;
                    } else {
                        pet.sex = petSex.PET_SEX_NOT_PROVIDED;
                    }
                    counter++;
                    break;
                case 4:
                    repository.readForm(counter);
                    System.out.println("\n.i - Write your house number:");
                    answer = input.nextLine();
                    pet.addrHouseNumber = answer;
                    System.out.println("\n.ii - Write your city:");
                    answer = input.nextLine();
                    pet.addrCity = answer;
                    System.out.println("\n.iii - Write your street:");
                    answer = input.nextLine();
                    pet.addrStreet = answer;
                    counter++;
                    break;
                case 5:
                    repository.readForm(counter);
                    answer = input.nextLine();
                    regex = "(\\d)+(\\.)*(\\d)*";
                    patter = Pattern.compile(regex);
                    matcher = patter.matcher(answer);
                    if (matcher.find()) {
                        pet.age = matcher.group();
                    } else {
                        pet.age = NOT_PROVIDED;
                    }
                    counter++;
                    break;
                case 6:
                    repository.readForm(counter);
                    answer = input.nextLine();
                    regex = "(\\d)+(\\.)*(\\d)*";
                    patter = Pattern.compile(regex);
                    matcher = patter.matcher(answer);
                    if (matcher.find()) {
                        pet.weight = matcher.group();
                    } else {
                        pet.weight = NOT_PROVIDED;
                    }
                    counter++;
                    break;
                case 7:
                    repository.readForm(counter);
                    answer = input.nextLine();
                    regex = "([a-zA-Z])+(\\s)*([a-zA-Z])*";
                    patter = Pattern.compile(regex);
                    matcher = patter.matcher(answer);
                    if (matcher.find()) {
                        pet.breed = matcher.group();
                    } else {
                        pet.breed = NOT_PROVIDED;
                    }
                    counter++;

                    System.out.println("Name: " + pet.name);
                    System.out.println("City: " + pet.addrCity);
                    System.out.println("Street: " + pet.addrStreet);
                    System.out.println("House Number: " + pet.addrHouseNumber);
                    System.out.println("Age: " + pet.age);
                    System.out.println("Weight: " + pet.weight);
                    System.out.println("Breed: " + pet.breed);
                    break;

            }
        }
    }

    public void updatePet() {

    }

    public void deletePet() {

    }

    public void listAllPets() {

    }

    public void listPetWithFilters() {

    }


}
